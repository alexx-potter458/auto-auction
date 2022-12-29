package pottertech.autoauctions.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="car_model_id")
    private CarModel carModel;

    @ManyToOne
    @JoinColumn(name="engine_id")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name="drivetrain_id")
    private Drivetrain drivetrain;
}
