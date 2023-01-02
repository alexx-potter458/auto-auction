package pottertech.autoauctions.dto;

import lombok.*;
import pottertech.autoauctions.entity.TractionType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    String carModel;
    String engineCode;
    String transmissionName;
    TractionType tractionType;
}
