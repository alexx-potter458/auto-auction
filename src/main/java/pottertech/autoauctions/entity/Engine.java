package pottertech.autoauctions.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long power;

    private Long torque;

    private Long capacity;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
}
