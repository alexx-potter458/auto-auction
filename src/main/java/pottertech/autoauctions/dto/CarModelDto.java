package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.entity.ChassisType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDto {
    @NotNull(message = "Car model name should not be null")
    private String name;
    private String manufacturer;
    private ChassisType chassisType;
    private String generation;
    private Long startYear;
    private Long endYear;
}