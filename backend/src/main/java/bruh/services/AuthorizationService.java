package bruh.services;

import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.repo.IPostgresRepo;
import bruh.util.encoder.PasswordEncoder;
import bruh.util.jwtproducer.JWTProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static bruh.util.constants.LoggerMessages.*;

@Slf4j
@Service
public class AuthorizationService {

    private final IPostgresRepo iPostgresRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTProducer jwtProducer;

    @Autowired
    public AuthorizationService(IPostgresRepo iPostgresRepo, PasswordEncoder passwordEncoder, JWTProducer jwtProducer) {
        this.iPostgresRepo = iPostgresRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtProducer = jwtProducer;
    }

    public String authorizeUser(User user) {
        User dbUser = iPostgresRepo.findUserByLogin(user.getLogin()).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, user.getLogin())));
        if (passwordEncoder.encode(user.getPassword()).equals(dbUser.getPassword())) {
            log.info(String.format(SUCCESSFULLY_AUTHORIZED, user.getLogin()));
            return jwtProducer.createJWT(user.getLogin());
        }

        throw new InvalidCredentialsException(String.format(USER_HAS_ANOTHER_PASSWORD, user.getLogin()));
    }
}
