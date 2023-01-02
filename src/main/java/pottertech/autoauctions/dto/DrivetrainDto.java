package pottertech.autoauctions.dto;

import lombok.*;
import pottertech.autoauctions.entity.TractionType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivetrainDto {
    private String transmissionName;
    private TractionType tractionType;
}
