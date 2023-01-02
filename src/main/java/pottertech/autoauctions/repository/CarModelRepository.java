package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.CarModel;

public interface CarModelRepository extends JpaRepository<CarModel, Long>  {
    CarModel findOneByName(String name);
}
