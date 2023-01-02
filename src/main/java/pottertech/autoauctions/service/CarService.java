package pottertech.autoauctions.service;

import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.entity.*;

public interface CarService {
    CarManufacturer addManufacturer(SimpleTypeDto simpleTypeDto);
    Transmission addTransmission(TransmissionDto transmissionDto);
    CarModel addCarModel(CarModelDto carModelDto);
    Drivetrain addDrivetrain(DrivetrainDto drivetrainDto);
    Engine addEngine(EngineDto engineDto);
    Car addCar(CarDto carDto);
}
