package pottertech.autoauctions.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.entity.*;
import pottertech.autoauctions.repository.*;

@Component
public class CarMapper {
    @Autowired
    CarManufacturerRepository carManufacturerRepository;

    @Autowired
    TransmissionRepository transmissionRepository;

    @Autowired
    EngineRepository engineRepository;

    @Autowired
    CarModelRepository carModelRepository;

    @Autowired
    DrivetrainRepository drivetrainRepository;

    @Autowired
    CarRepository carRepository;

    public CarManufacturer typeToCarManufacturer(SimpleTypeDto simpleTypeDto) {
        return CarManufacturer.builder()
                .name(simpleTypeDto.getName())
                .build();
    }

    public Transmission transmissionDtoToTransmission(TransmissionDto transmissionDto) {
        return Transmission.builder()
                .name(transmissionDto.getName())
                .type(transmissionDto.getType())
                .speeds(transmissionDto.getSpeeds())
                .build();
    }

    public CarModel carModelDtoToCarModel(CarModelDto carModelDto) {
        return CarModel.builder()
                .name(carModelDto.getName())
                .chassisType(carModelDto.getChassisType())
                .generation(carModelDto.getGeneration())
                .endYear(carModelDto.getEndYear())
                .startYear(carModelDto.getStartYear())
                .carManufacturer(this.carManufacturerRepository.findOneByName(carModelDto.getManufacturer()))
                .build();
    }

    public Drivetrain drivetrainDtoToDrivetrain(DrivetrainDto drivetrainDto) {
        return Drivetrain.builder()
                .transmission(this.transmissionRepository.findOneByName(drivetrainDto.getTransmissionName()))
                .tractionType(drivetrainDto.getTractionType())
                .build();
    }

    public Engine engineDtoToEngine(EngineDto engineDto) {
        return Engine.builder()
                .name(engineDto.getName())
                .power(engineDto.getPower())
                .capacity(engineDto.getCapacity())
                .torque(engineDto.getTorque())
                .fuelType(engineDto.getFuelType())
                .build();
    }

    public Car carDtoToCar(CarDto carDto) {
        return Car.builder()
                .carModel(this.carModelRepository.findOneByName(carDto.getCarModel()))
                .engine(this.engineRepository.findOneByName(carDto.getEngineCode()))
                .drivetrain(this.drivetrainRepository.findOneByTransmissionAndTractionType(this.transmissionRepository.findOneByName(carDto.getTransmissionName()), carDto.getTractionType()))
                .build();
    }

    public CarDetails reportDtoToCarDetails(ReportDto reportDto) {
        Car car = this.carDtoToCar(reportDto.getCar());

        return CarDetails.builder()
                .kilometrage(reportDto.getKilometrage())
                .year(reportDto.getYear())
                .price(reportDto.getPrice())
                .car(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(car.getCarModel(), car.getEngine(), car.getDrivetrain()))
                .build();
    }
}
