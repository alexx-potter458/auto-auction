package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.CarManufacturer;

public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, Long> {
    CarManufacturer findOneByName(String name);
}
