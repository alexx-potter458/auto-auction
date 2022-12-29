package pottertech.autoauctions.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drivetrain")
public class Drivetrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="transmission_id")
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    private TractionType tractionType;
}
