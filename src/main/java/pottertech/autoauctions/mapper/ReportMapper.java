package pottertech.autoauctions.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.entity.CarDetails;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.repository.*;

@Component
public class ReportMapper {
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    CarDetailsRepository carDetailsRepository;

    @Autowired
    CarMapper carMapper;

    public Report reportDtoToReport(ReportDto reportDto){
        CarDetails carDetails = this.carMapper.reportDtoToCarDetails(reportDto);

        return Report.builder()
                .user(this.tokenRepository.findOneByName(reportDto.getUserToken()).getUser())
                .carDetails(this.carDetailsRepository.findOneByCarAndKilometrageAndYearAndPrice(carDetails.getCar(), carDetails.getKilometrage(), carDetails.getYear(), carDetails.getPrice()))
                .build();
    }
}
