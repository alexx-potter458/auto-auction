package pottertech.autoauctions.dto;

import lombok.*;
import pottertech.autoauctions.entity.TractionType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private String userToken;
    private Long kilometrage;
    private Long year;
    private Long price;
    private String carModel;
    private String engineCode;
    private String transmissionName;
    private TractionType tractionType;
}
