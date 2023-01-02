package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Engine;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    Engine findOneByName(String name);
}
