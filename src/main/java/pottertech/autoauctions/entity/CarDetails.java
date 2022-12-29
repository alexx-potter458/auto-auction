package pottertech.autoauctions.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_details")
public class CarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kilometrage;

    private Long year;

    private Long price;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;
}
