package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.entity.FuelType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EngineDto {
    @NotNull(message = Constants.NAME_NOT_NULL)
    private String name;

    private Long power;
    private Long torque;
    private Double capacity;
    private FuelType fuelType;
}
