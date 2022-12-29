package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Transmission;

import java.util.List;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
    List<Transmission> findAll();
}
