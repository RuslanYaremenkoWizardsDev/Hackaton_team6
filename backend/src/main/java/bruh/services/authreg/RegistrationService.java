package bruh.services.authreg;

import bruh.entity.User;
import bruh.repo.IUserRepo;
import bruh.util.encoder.PasswordEncoder;
import bruh.util.powergenerator.PowerGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static bruh.util.constants.LoggerMessages.SUCCESSFULLY_REGISTERED;

@Slf4j
@Service
public class RegistrationService {

    private final IUserRepo iUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final PowerGenerator powerGenerator;

    @Autowired
    public RegistrationService(IUserRepo iUserRepo, PasswordEncoder passwordEncoder, PowerGenerator powerGenerator) {
        this.iUserRepo = iUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.powerGenerator = powerGenerator;
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPower(powerGenerator.generateRandomPower());
        iUserRepo.save(user);
        log.info(String.format(SUCCESSFULLY_REGISTERED, user.getLogin()));
    }
}
