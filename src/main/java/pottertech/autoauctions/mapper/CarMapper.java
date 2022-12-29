package pottertech.autoauctions.mapper;

import org.springframework.stereotype.Component;
import pottertech.autoauctions.dto.SimpleTypeDto;
import pottertech.autoauctions.entity.CarManufacturer;

@Component
public class CarMapper {
    public CarManufacturer typeToCarManufacturer(SimpleTypeDto simpleTypeDto) {
        return CarManufacturer.builder()
                .name(simpleTypeDto.getName())
                .build();
    }
}
