package pottertech.autoauctions.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortReportDto {
    private Long reportId;
    private String name;
    private Long carKilometrage;
    private Long carYear;
    private Long carPrice;
    private String carName;
    private String engine;
    private String drivetrain;
    private boolean approved;
    private boolean bought;
}
