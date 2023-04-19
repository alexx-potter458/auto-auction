package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByRole(String role);
}
