package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Drivetrain;
import pottertech.autoauctions.entity.TractionType;
import pottertech.autoauctions.entity.Transmission;

public interface DrivetrainRepository extends JpaRepository<Drivetrain, Long> {
    Drivetrain findOneByTransmissionAndTractionType(Transmission transmission, TractionType tractionType);
}
