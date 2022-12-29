package pottertech.autoauctions.mapper;

import org.springframework.stereotype.Component;
import pottertech.autoauctions.dto.SimpleTypeDto;
import pottertech.autoauctions.dto.TransmissionDto;
import pottertech.autoauctions.entity.CarManufacturer;
import pottertech.autoauctions.entity.Transmission;

@Component
public class CarMapper {
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
}
