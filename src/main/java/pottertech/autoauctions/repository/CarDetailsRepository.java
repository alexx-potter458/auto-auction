package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Car;
import pottertech.autoauctions.entity.CarDetails;

public interface CarDetailsRepository extends JpaRepository<CarDetails, Long> {
    CarDetails findOneByCarAndKilometrageAndYearAndPrice(Car car, Long kilometrage, Long years, Long price);
}
