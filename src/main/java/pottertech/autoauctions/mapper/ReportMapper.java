package pottertech.autoauctions.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.repository.CarRepository;
import pottertech.autoauctions.repository.DrivetrainRepository;
import pottertech.autoauctions.repository.EngineRepository;

public class ReportMapper {
    @Autowired
    CarRepository carRepository;

    @Autowired
    EngineRepository engineRepository;

    @Autowired
    DrivetrainRepository drivetrainRepository;

    public Report reportDtoToReport(){
        return Report.builder().build();
    }
}
