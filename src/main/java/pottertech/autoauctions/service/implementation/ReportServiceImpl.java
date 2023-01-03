package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.entity.Car;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.mapper.ReportMapper;
import pottertech.autoauctions.repository.CarDetailsRepository;
import pottertech.autoauctions.repository.CarRepository;
import pottertech.autoauctions.repository.ReportRepository;
import pottertech.autoauctions.service.ReportService;

import java.util.List;

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
        return this.reportRepository.findAll();
    }

    @Override
    public Report addReport(ReportDto reportDto) {
        Car car = this.carMapper.carDtoToCar(reportDto.getCar());

        if(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(car.getCarModel(), car.getEngine(), car.getDrivetrain()) == null)
            this.carRepository.save(car);

        this.carDetailsRepository.save(carMapper.reportDtoToCarDetails(reportDto));

        return this.reportRepository.save(this.reportMapper.reportDtoToReport(reportDto));
    }
}
