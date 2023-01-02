package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.entity.*;
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

    @Override
    public CarManufacturer addManufacturer(SimpleTypeDto simpleTypeDto) {
        if(this.carManufacturerRepository.findOneByName(simpleTypeDto.getName()) != null)
            throw new CarException(Constants.CAR_MANUFACTURER_ALREADY_EXISTS);

        return this.carManufacturerRepository.save(this.carMapper.typeToCarManufacturer(simpleTypeDto));
    }

    @Override
    public Transmission addTransmission(TransmissionDto transmissionDto) {
        if(this.carManufacturerRepository.findOneByName(transmissionDto.getName()) != null)
            throw new CarException(Constants.TRANSMISSION_ALREADY_EXISTS);

        return this.transmissionRepository.save(this.carMapper.transmissionDtoToTransmission(transmissionDto));
    }

    @Override
    public CarModel addCarModel(CarModelDto carModelDto) {
        if(this.carModelRepository.findOneByName(carModelDto.getName()) != null)
            throw new CarException(Constants.CAR_MODEL_ALREADY_EXISTS);

        if(this.carManufacturerRepository.findOneByName(carModelDto.getManufacturer()) == null)
            throw new CarException(Constants.CAR_MANUFACTURER_NOT_EXISTS);

        return this.carModelRepository.save(this.carMapper.carModelDtoToCarModel(carModelDto));
    }

    @Override
    public Drivetrain addDrivetrain(DrivetrainDto drivetrainDto) {
        return this.drivetrainRepository.save(carMapper.drivetrainDtoToDrivetrain(drivetrainDto));
    }

    @Override
    public Engine addEngine(EngineDto engineDto) {
        return this.engineRepository.save(this.carMapper.engineDtoToEngine(engineDto));
    }

    @Override
    public Car addCar(CarDto carDto) {
        return  this.carRepository.save(this.carMapper.carDtoToCar(carDto));
    }
}
