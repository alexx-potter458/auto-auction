package pottertech.autoauctions.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.Car;
import pottertech.autoauctions.entity.CarDetails;
import pottertech.autoauctions.entity.Report;
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

    Logger log = LoggerFactory.getLogger(ReportService.class);

    @Override
    public Page<ShortReportDto> getAllReportsShort(Pageable pageable) {
        log.info("---> Getting all reports...");

        Page<Report> reportList = this.reportRepository.findAll(pageable);
        Page<ShortReportDto> shortReportList = reportList
                .map(report -> reportMapper.reportToShortReport(report));

        if (reportList.isEmpty()) {
            log.warn("---> No report found!");
            throw new UserException(Constants.NO_REPORT_FOUND);
        }

        log.info("---> Got reports!");
        return shortReportList;
    }

    @Override
    public Page<ShortReportDto> getFilteredReports(FilterDto parameters, Pageable pageable) {
        log.info("---> Getting all reports...");

        List<ShortReportDto> reportList = this.reportRepository.findAll(pageable)
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

        if (reportList.isEmpty()){
            log.warn("---> No report found!");
            throw new UserException(Constants.NO_REPORT_FOUND);
        }

        log.info("---> Got reports!");
        return new PageImpl<>(reportList, pageable, reportList.size());
    }

    @Override
    public Report addReport(ReportDto reportDto, String username) {
        log.info("---> Adding a report...");

        Car car = this.carMapper.reportDtoDtoToCar(reportDto);
        if(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(car.getCarModel(), car.getEngine(), car.getDrivetrain()) == null) {
            log.info("---> Car not found, adding car...");
            this.carRepository.save(car);
        }

        CarDetails carDetails = this.carMapper.reportDtoToCarDetails(reportDto);

        if(this.carDetailsRepository.findOneByCarAndKilometrageAndYearAndPrice(carDetails.getCar(), carDetails.getKilometrage(), carDetails.getYear(), carDetails.getPrice()) == null){
            log.info("---> Saving car details...");
            this.carDetailsRepository.save(carMapper.reportDtoToCarDetails(reportDto));
        }

        log.info("---> Saving report...");
        return this.reportRepository.save(this.reportMapper.reportDtoToReport(reportDto, username));
    }

    @Override
    public void approveReport(ReportApprovalDto reportApprovalDto) {
        log.info("---> Approving report!");

        Report report = this.reportRepository.findOneById(reportApprovalDto.getReportId());
        report.setApproved(true);
        this.reportRepository.save(report);
    }

    @Override
    public void buyCar(ReportApprovalDto reportApprovalDto) {
        log.info("---> Approving report!");

        Report report = this.reportRepository.findOneById(reportApprovalDto.getReportId());
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
