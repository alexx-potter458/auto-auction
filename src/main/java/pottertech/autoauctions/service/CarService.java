package pottertech.autoauctions.service;

import pottertech.autoauctions.dto.SimpleTypeDto;
import pottertech.autoauctions.entity.CarManufacturer;

public interface CarService {
    CarManufacturer addManufacturer(SimpleTypeDto simpleTypeDto);
}
