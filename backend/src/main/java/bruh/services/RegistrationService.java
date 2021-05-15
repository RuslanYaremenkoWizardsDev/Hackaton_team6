package bruh.services;

import bruh.entity.User;
import bruh.repo.IPostgresRepo;
import bruh.util.encoder.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static bruh.util.constants.LoggerMessages.SUCCESSFULLY_REGISTERED;

@Slf4j
@Service
public class RegistrationService {

    private final IPostgresRepo iPostgresRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(IPostgresRepo iPostgresRepo, PasswordEncoder passwordEncoder) {
        this.iPostgresRepo = iPostgresRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        iPostgresRepo.save(user);
        log.info(String.format(SUCCESSFULLY_REGISTERED, user.getLogin()));
    }
}
