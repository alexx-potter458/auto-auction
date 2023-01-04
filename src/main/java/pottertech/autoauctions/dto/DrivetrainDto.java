package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.entity.TractionType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivetrainDto {
    @NotNull(message = Constants.NAME_NOT_NULL)
    private String transmissionName;

    @NotNull(message = Constants.TYPE_NOT_NULL)
    private TractionType tractionType;
}
