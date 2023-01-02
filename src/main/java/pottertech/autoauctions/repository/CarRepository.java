package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.*;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findOneByCarModelAndEngineAndDrivetrain(CarModel carModel, Engine engine, Drivetrain drivetrain);
}
