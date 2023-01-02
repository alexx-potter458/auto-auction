package pottertech.autoauctions.dto;

import lombok.*;
import pottertech.autoauctions.entity.FuelType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EngineDto {
    private String name;
    private Long power;
    private Long torque;
    private Double capacity;
    private FuelType fuelType;
}
