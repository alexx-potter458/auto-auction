package pottertech.autoauctions.dto;

import lombok.*;

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
    private CarDto car;
}
