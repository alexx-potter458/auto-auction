package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.CarDetails;

public interface CarDetailsRepository extends JpaRepository<CarDetails, Long> {
}
