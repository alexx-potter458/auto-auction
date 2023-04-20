package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pottertech.autoauctions.entity.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @SuppressWarnings("NullableProblems")
    List<User> findAll();

    User findOneByEmail(String email);
    User findOneByUsername(String username);
    User findOneByEmailAndPassword(String email, String password);
    User findOneByEmailOrUsername(String email, String username);
}
