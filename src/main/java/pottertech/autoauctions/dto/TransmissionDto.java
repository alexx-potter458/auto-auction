package pottertech.autoauctions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pottertech.autoauctions.entity.TransmissionType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransmissionDto {
    @NotNull(message = "Car transmission name should not be null")
    private String name;
    private TransmissionType type;
    private Long speeds;
}
