package pottertech.autoauctions.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.CarDetails;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.repository.*;

@Component
public class ReportMapper {
    @Autowired
    CarDetailsRepository carDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarMapper carMapper;

    public Report reportDtoToReport(ReportDto reportDto, String username){
        CarDetails carDetails = this.carMapper.reportDtoToCarDetails(reportDto);

        return Report.builder()
                .user(this.userRepository.findOneByUsername(username))
                .carDetails(this.carDetailsRepository.findOneByCarAndKilometrageAndYearAndPrice(carDetails.getCar(), carDetails.getKilometrage(), carDetails.getYear(), carDetails.getPrice()))
                .isApproved(false)
                .isBought(false)
                .build();
    }

    public ShortReportDto reportToShortReport(Report report) {
        return ShortReportDto.builder()
                .reportId(report.getId())
                .name(report.getUser().getFirstname() + " " + report.getUser().getLastname())
                .carKilometrage(report.getCarDetails().getKilometrage())
                .carName(report.getCarDetails().getCar().getCarModel().getCarManufacturer().getName() + " " + report.getCarDetails().getCar().getCarModel().getName())
                .carYear(report.getCarDetails().getYear())
                .engine(report.getCarDetails().getCar().getEngine().getCapacity() + "L "+
                        report.getCarDetails().getCar().getEngine().getName() + " " +
                        report.getCarDetails().getCar().getEngine().getFuelType() + " "+
                        report.getCarDetails().getCar().getEngine().getPower() + "CP " +
                        report.getCarDetails().getCar().getEngine().getTorque() + "NM")
                .drivetrain(report.getCarDetails().getCar().getDrivetrain().getTransmission().getName() + " " + report.getCarDetails().getCar().getDrivetrain().getTractionType())
                .carPrice(report.getCarDetails().getPrice())
                .approved(report.isApproved())
                .bought(report.isBought())
                .build();
    }
}
