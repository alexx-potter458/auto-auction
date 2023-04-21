package pottertech.autoauctions.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.entity.*;
import pottertech.autoauctions.exception.BadPayloadException;
import pottertech.autoauctions.exception.CarException;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.repository.*;
import pottertech.autoauctions.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarManufacturerRepository carManufacturerRepository;

    @Autowired
    TransmissionRepository transmissionRepository;

    @Autowired
    CarModelRepository carModelRepository;

    @Autowired
    DrivetrainRepository drivetrainRepository;

    @Autowired
    EngineRepository engineRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarMapper carMapper;

    Logger log = LoggerFactory.getLogger(CarService.class);

    @Override
    public CarManufacturer addManufacturer(SimpleTypeDto simpleTypeDto) {
        log.info("---> Trying to add manufacturer...");

        if(this.carManufacturerRepository.findOneByName(simpleTypeDto.getName()) != null) {
            log.warn("---> Manufacturer existing.");
            throw new CarException(Constants.CAR_MANUFACTURER_ALREADY_EXISTS);
        }

        log.info("---> Manufacturer validated...");

        return this.carManufacturerRepository.save(this.carMapper.typeToCarManufacturer(simpleTypeDto));
    }

    @Override
    public Transmission addTransmission(TransmissionDto transmissionDto) {
        log.info("---> Trying to add transmission...");
        if(this.transmissionRepository.findOneByName(transmissionDto.getName()) != null)
            throw new CarException(Constants.TRANSMISSION_ALREADY_EXISTS);

        log.info("---> Transmission validated...");

        return this.transmissionRepository.save(this.carMapper.transmissionDtoToTransmission(transmissionDto));
    }

    @Override
    public CarModel addCarModel(CarModelDto carModelDto) {
        log.info("---> Trying to add car model...");
        if(this.carModelRepository.findOneByName(carModelDto.getName()) != null) {
            log.warn("---> Car model model existing.");
            throw new CarException(Constants.CAR_MODEL_ALREADY_EXISTS);
        }

        if(this.carManufacturerRepository.findOneByName(carModelDto.getManufacturer()) == null) {
            log.warn("---> Car manufacturer existing.");
            throw new BadPayloadException(Constants.CAR_MANUFACTURER_NOT_EXISTS);
        }

        log.info("---> Car model validated...");

        return this.carModelRepository.save(this.carMapper.carModelDtoToCarModel(carModelDto));
    }

    @Override
    public Drivetrain addDrivetrain(DrivetrainDto drivetrainDto) {
        log.info("---> Trying to add drivetrain...");
        if(this.drivetrainRepository.findOneByTransmissionAndTractionType(this.transmissionRepository.findOneByName(drivetrainDto.getTransmissionName()), drivetrainDto.getTractionType()) != null) {
            log.warn("---> Existing drivetrain found.");
            throw new CarException(Constants.DRIVETRAIN_ALREADY_EXISTS);
        }

        if(this.transmissionRepository.findOneByName(drivetrainDto.getTransmissionName()) == null) {
            log.info("---> No transmission found.");
            throw new BadPayloadException(Constants.TRANSMISSION_NOT_FOUND);
        }

        log.info("---> Drivetrain validated...");

        return this.drivetrainRepository.save(carMapper.drivetrainDtoToDrivetrain(drivetrainDto));
    }

    @Override
    public Engine addEngine(EngineDto engineDto) {
        log.info("---> Trying to add engine...");
        if(this.engineRepository.findOneByName(engineDto.getName()) != null)
            throw new CarException(Constants.ENGINE_ALREADY_EXISTS);

        log.info("---> Engine validated...");

        return this.engineRepository.save(this.carMapper.engineDtoToEngine(engineDto));
    }

    @Override
    public Car addCar(CarDto carDto) {
        log.info("---> Trying to add car...");
        Car car = carMapper.carDtoToCar(carDto);

        if(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(car.getCarModel(), car.getEngine(), car.getDrivetrain()) != null){
            log.error("---> No car found.");
            throw new CarException(Constants.CAR_ALREADY_EXISTS);
        }

        if(this.carModelRepository.findOneByName(carDto.getCarModel()) == null) {
            log.error("---> No car model found.");
            throw new BadPayloadException(Constants.CAR_MODEL_NOT_FOUND);
        }

        if(this.engineRepository.findOneByName(carDto.getEngineCode()) == null){
            log.error("---> No engine found.");
            throw new BadPayloadException(Constants.ENGINE_NOT_FOUND);
        }

        if(this.drivetrainRepository.findOneByTransmissionAndTractionType(this.transmissionRepository.findOneByName(carDto.getTransmissionName()), carDto.getTractionType()) == null){
            log.error("---> No drivetrain found.");
            throw new BadPayloadException(Constants.DRIVETRAIN_NOT_FOUND);
        }

        log.info("---> Car validated...");

        return  this.carRepository.save(this.carMapper.carDtoToCar(carDto));
    }
}
