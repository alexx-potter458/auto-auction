package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.entity.TransmissionType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransmissionDto {
    @NotNull(message = Constants.TRANSMISSION_NOT_NULL)
    private String name;

    @NotNull(message = Constants.TYPE_NOT_NULL)
    private TransmissionType type;

    @NotNull(message = Constants.SPEEDS_NOT_NULL)
    private Long speeds;
}
