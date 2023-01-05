package pottertech.autoauctions.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.entity.*;
import pottertech.autoauctions.exception.BadPayloadException;
import pottertech.autoauctions.exception.CarException;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.repository.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {
    @Mock
    CarManufacturerRepository carManufacturerRepository;

    @Mock
    TransmissionRepository transmissionRepository;

    @Mock
    CarModelRepository carModelRepository;

    @Mock
    DrivetrainRepository drivetrainRepository;

    @Mock
    EngineRepository engineRepository;

    @Mock
    CarRepository carRepository;

    @Mock
    CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void addManufacturerSuccess() {
        when(this.carManufacturerRepository.findOneByName(any())).thenReturn(null);
        when(this.carManufacturerRepository.save(any())).thenReturn(CarManufacturer.builder().build());

        CarManufacturer response = this.carService.addManufacturer(SimpleTypeDto.builder().build());

        assertNotNull(response);
    }

    @Test
    void addManufacturerFail() {
        when(this.carManufacturerRepository.findOneByName(any())).thenReturn(CarManufacturer.builder().build());

        Exception exception = assertThrows(CarException.class, () -> this.carService.addManufacturer(SimpleTypeDto.builder().build()));

        assertEquals(Constants.CAR_MANUFACTURER_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void addTransmissionSuccess() {
        when(this.transmissionRepository.findOneByName(any())).thenReturn(null);
        when(this.transmissionRepository.save(any())).thenReturn(Transmission.builder().build());

        Transmission response = this.carService.addTransmission(TransmissionDto.builder().build());

        assertNotNull(response);
    }

    @Test
    void addTransmissionFail() {
        when(this.transmissionRepository.findOneByName(any())).thenReturn(Transmission.builder().build());

        Exception exception = assertThrows(CarException.class, () -> this.carService.addTransmission(TransmissionDto.builder().build()));

        assertEquals(Constants.TRANSMISSION_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void addCarModel() {
        when(this.carModelRepository.findOneByName(any())).thenReturn(null);
        when(this.carManufacturerRepository.findOneByName(any())).thenReturn(CarManufacturer.builder().build());
        when(this.carModelRepository.save(any())).thenReturn(CarModel.builder().build());

        CarModel response = this.carService.addCarModel(CarModelDto.builder().build());

        assertNotNull(response);
    }

    @Test
    void addCarModelFail() {
        when(this.carModelRepository.findOneByName(any())).thenReturn(CarModel.builder().build());

        Exception exception = assertThrows(CarException.class, () -> this.carService.addCarModel(CarModelDto.builder().build()));

        assertEquals(Constants.CAR_MODEL_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void addCarModelFail2() {
        when(this.carModelRepository.findOneByName(any())).thenReturn(null);
        when(this.carManufacturerRepository.findOneByName(any())).thenReturn(null);

        Exception exception = assertThrows(BadPayloadException.class, () -> this.carService.addCarModel(CarModelDto.builder().build()));

        assertEquals(Constants.CAR_MANUFACTURER_NOT_EXISTS, exception.getMessage());
    }

    @Test
    void addDrivetrain() {
        when(this.drivetrainRepository.findOneByTransmissionAndTractionType(any(), any())).thenReturn(null);
        when(this.transmissionRepository.findOneByName(any())).thenReturn(new Transmission());
        when(this.drivetrainRepository.save(any())).thenReturn(Drivetrain.builder().build());

        Drivetrain response = this.carService.addDrivetrain(DrivetrainDto.builder().build());

        assertNotNull(response);
    }

    @Test
    void addDrivetrainFail() {
        when(this.drivetrainRepository.findOneByTransmissionAndTractionType(any(), any())).thenReturn(Drivetrain.builder().build());

        Exception exception = assertThrows(CarException.class, () -> this.carService.addDrivetrain(DrivetrainDto.builder().build()));

        assertEquals(Constants.DRIVETRAIN_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void addDrivetrainFail1() {
        when(this.drivetrainRepository.findOneByTransmissionAndTractionType(any(), any())).thenReturn(null);

        Exception exception = assertThrows(BadPayloadException.class, () -> this.carService.addDrivetrain(DrivetrainDto.builder().build()));

        assertEquals(Constants.TRANSMISSION_NOT_FOUND, exception.getMessage());
    }

    @Test
    void addEngine() {
        when(this.engineRepository.findOneByName(any())).thenReturn(null);
        when(this.engineRepository.save(any())).thenReturn(Engine.builder().build());

        Engine response = this.carService.addEngine(EngineDto.builder().build());

        assertNotNull(response);
    }

    @Test
    void addEngineFail() {
        when(this.engineRepository.findOneByName(any())).thenReturn(Engine.builder().build());

        Exception exception = assertThrows(CarException.class, () -> this.carService.addEngine(EngineDto.builder().build()));

        assertEquals(Constants.ENGINE_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void addCar() {
        when(carMapper.carDtoToCar(any())).thenReturn(new Car());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(null);
        when(this.engineRepository.findOneByName(any())).thenReturn(Engine.builder().build());
        when(this.carModelRepository.findOneByName(any())).thenReturn(new CarModel());
        when(this.drivetrainRepository.findOneByTransmissionAndTractionType(any(), any())).thenReturn(Drivetrain.builder().build());

        when(this.carRepository.save(any())).thenReturn(new Car());

        Car response = this.carService.addCar(CarDto.builder().build());

        assertNotNull(response);
    }

    @Test
    void addCarFail() {
        when(carMapper.carDtoToCar(any())).thenReturn(new Car());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(new Car());

        Exception exception = assertThrows(CarException.class, () -> this.carService.addCar(CarDto.builder().build()));

        assertEquals(Constants.CAR_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void addCarFail2() {
        when(carMapper.carDtoToCar(any())).thenReturn(new Car());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(null);

        Exception exception = assertThrows(BadPayloadException.class, () -> this.carService.addCar(CarDto.builder().build()));

        assertEquals(Constants.CAR_MODEL_NOT_FOUND, exception.getMessage());
    }

    @Test
    void addCarFail3() {
        when(carMapper.carDtoToCar(any())).thenReturn(new Car());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(null);
        when(this.carModelRepository.findOneByName(any())).thenReturn(new CarModel());

        Exception exception = assertThrows(BadPayloadException.class, () -> this.carService.addCar(CarDto.builder().build()));

        assertEquals(Constants.ENGINE_NOT_FOUND, exception.getMessage());
    }

    @Test
    void addCarFail4() {
        when(carMapper.carDtoToCar(any())).thenReturn(new Car());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(null);
        when(this.carModelRepository.findOneByName(any())).thenReturn(new CarModel());
        when(this.engineRepository.findOneByName(any())).thenReturn(null);

        Exception exception = assertThrows(BadPayloadException.class, () -> this.carService.addCar(CarDto.builder().build()));

        assertEquals(Constants.ENGINE_NOT_FOUND, exception.getMessage());
    }

    @Test
    void addCarFail5() {
        when(carMapper.carDtoToCar(any())).thenReturn(new Car());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(null);
        when(this.carModelRepository.findOneByName(any())).thenReturn(new CarModel());
        when(this.engineRepository.findOneByName(any())).thenReturn(new Engine());

        Exception exception = assertThrows(BadPayloadException.class, () -> this.carService.addCar(CarDto.builder().build()));

        assertEquals(Constants.DRIVETRAIN_NOT_FOUND, exception.getMessage());
    }
}