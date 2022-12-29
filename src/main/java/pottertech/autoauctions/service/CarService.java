package pottertech.autoauctions.service;

import pottertech.autoauctions.dto.SimpleTypeDto;
import pottertech.autoauctions.dto.TransmissionDto;
import pottertech.autoauctions.entity.CarManufacturer;
import pottertech.autoauctions.entity.Transmission;

public interface CarService {
    CarManufacturer addManufacturer(SimpleTypeDto simpleTypeDto);
    Transmission addTransmission(TransmissionDto transmissionDto);
}
