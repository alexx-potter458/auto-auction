package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.entity.ChassisType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDto {
    @NotNull(message = Constants.NAME_NOT_NULL)
    private String name;

    @NotNull(message = Constants.MANUFACTURER_NOT_NULL)
    private String manufacturer;

    @NotNull(message = Constants.TYPE_NOT_NULL)
    private ChassisType chassisType;

    @NotNull(message = Constants.GENERATION_NOT_NULL)
    private String generation;

    @NotNull(message = Constants.START_YEAR_NOT_NULL)
    private Long startYear;

    @NotNull(message = Constants.END_YEAR_NOT_NULL)
    private Long endYear;
}