package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Token;
import pottertech.autoauctions.entity.User;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findOneByUser(User user);
}
