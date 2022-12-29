package pottertech.autoauctions.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "top_speed")
    private Double topSpeed;

    @Column(name = "acceleration_time")
    private Double accelerationTime;

    @OneToOne
    @JoinColumn(name="car_id")
    private Car car;
}
