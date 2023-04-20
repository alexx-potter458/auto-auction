package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.Car;
import pottertech.autoauctions.entity.CarDetails;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.exception.ReportException;
import pottertech.autoauctions.exception.UserException;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.mapper.ReportMapper;
import pottertech.autoauctions.repository.CarDetailsRepository;
import pottertech.autoauctions.repository.CarRepository;
import pottertech.autoauctions.repository.ReportRepository;
import pottertech.autoauctions.service.ReportService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    CarDetailsRepository carDetailsRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    CarMapper carMapper;

    @Override
    public List<Report> getAllReports() {
        List<Report> reportList = this.reportRepository.findAll();

        if (reportList.isEmpty())
            throw new UserException(Constants.NO_REPORT_FOUND);

        return reportList;
    }

    @Override
    public Report getReport(Long id) {
        Report report = this.reportRepository.findOneById(id);

        if (report == null)
            throw new UserException(Constants.NO_REPORT_FOUND);

        return report;
    }

    @Override
    public List<ShortReportDto> getAllReportsShort() {
        List<ShortReportDto> reportList = this.reportRepository.findAll()
                .stream()
                .map(report -> reportMapper.reportToShortReport(report))
                .collect(Collectors.toList());

        if (reportList.isEmpty())
            throw new UserException(Constants.NO_REPORT_FOUND);

        return reportList;
    }

    @Override
    public List<ShortReportDto> getFilteredReports(FilterDto parameters) {
        List<ShortReportDto> reportList = this.reportRepository.findAll()
                .stream()
                .filter(report -> this.verifyGreaterNumber(parameters.getMaxPower(), report.getCarDetails().getCar().getEngine().getPower()))
                .filter(report -> this.verifyGreaterNumber(parameters.getMaxKilometrage(), report.getCarDetails().getKilometrage()))
                .filter(report -> this.verifyGreaterNumber(parameters.getMaxPrice(), report.getCarDetails().getPrice()))
                .filter(report -> this.verifyGreaterNumber(parameters.getMaxYear(), report.getCarDetails().getYear()))
                .filter(report -> this.verifyGreaterNumber(report.getCarDetails().getCar().getEngine().getPower(), parameters.getMinPower()))
                .filter(report -> this.verifyGreaterNumber(report.getCarDetails().getKilometrage(), parameters.getMinKilometrage()))
                .filter(report -> this.verifyGreaterNumber(report.getCarDetails().getPrice(), parameters.getMinPrice()))
                .filter(report -> this.verifyGreaterNumber(report.getCarDetails().getYear(), parameters.getMinYear()))
                .filter(report -> this.verifyString(parameters.getTractionType(), report.getCarDetails().getCar().getDrivetrain().getTractionType().name()))
                .filter(report -> this.verifyString(parameters.getFuelType(), report.getCarDetails().getCar().getEngine().getFuelType().toString()))
                .filter(report -> this.verifyString(parameters.getGearbox(), report.getCarDetails().getCar().getDrivetrain().getTransmission().getType().toString()))
                .map(report -> reportMapper.reportToShortReport(report))
                .collect(Collectors.toList());

        if (reportList.isEmpty())
            throw new UserException(Constants.NO_REPORT_FOUND);

        return reportList;
    }

    @Override
    public Report addReport(ReportDto reportDto, String username) {
        Car car = this.carMapper.reportDtoDtoToCar(reportDto);
        if(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(car.getCarModel(), car.getEngine(), car.getDrivetrain()) == null)
            this.carRepository.save(car);

        CarDetails carDetails = this.carMapper.reportDtoToCarDetails(reportDto);

        if(this.carDetailsRepository.findOneByCarAndKilometrageAndYearAndPrice(carDetails.getCar(), carDetails.getKilometrage(), carDetails.getYear(), carDetails.getPrice()) == null)
            this.carDetailsRepository.save(carMapper.reportDtoToCarDetails(reportDto));

        return this.reportRepository.save(this.reportMapper.reportDtoToReport(reportDto, username));
    }

    @Override
    public void approveReport(ReportApprovalDto reportApprovalDto) {
        Report report = this.reportRepository.findOneById(reportApprovalDto.getReportId());
        report.setApproved(true);
        this.reportRepository.save(report);
    }

    @Override
    public void buyCar(ReportApprovalDto reportApprovalDto) {
        Report report = this.reportRepository.findOneById(reportApprovalDto.getReportId());

        if(report.isBought())
            throw new ReportException(Constants.CAR_AlREADY_BOUGHT);

        report.setBought(true);
        this.reportRepository.save(report);
    }

    private <T> boolean  verifyString(T queryValue, String reportValue) {
        if(queryValue == null || reportValue == null)
            return true;
        return queryValue.toString().equals(reportValue);
    }

    private boolean verifyGreaterNumber(Long bigNumber, Long smallNumber) {
        if(bigNumber == null || smallNumber == null)
            return true;
        return bigNumber >= smallNumber;
    }
}
