package bruh.repo;

import bruh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepo extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);

}
