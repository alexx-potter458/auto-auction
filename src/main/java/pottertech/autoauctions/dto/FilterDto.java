package pottertech.autoauctions.dto;

import lombok.*;
import pottertech.autoauctions.entity.FuelType;
import pottertech.autoauctions.entity.TractionType;
import pottertech.autoauctions.entity.TransmissionType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilterDto {
    private Long maxPrice;
    private Long minPrice;
    private Long maxKilometrage;
    private Long minKilometrage;
    private Long maxPower;
    private Long minPower;
    private Long maxYear;
    private Long minYear;
    private TransmissionType gearbox;
    private FuelType fuelType;
    private TractionType tractionType;
}
