package pottertech.autoauctions.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String generation;
    @Column(name = "start_year")
    private Long startYear;
    @Column(name = "end_year")
    private Long endYear;

    @ManyToOne
    @JoinColumn(name="chassis_type_id")
    private ChassisType chassisType;

    @ManyToOne
    @JoinColumn(name="car_manufacturer_id")
    private CarManufacturer carManufacturer;
}
