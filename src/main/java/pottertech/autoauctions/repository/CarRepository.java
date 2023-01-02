package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
