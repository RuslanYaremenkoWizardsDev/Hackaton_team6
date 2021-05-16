package bruh.services;

import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.repo.IPostgresRepo;
import bruh.util.encoder.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import static bruh.util.constants.LoggerMessages.SUCCESSFULLY_CHANGE_PASSWORD;
import static bruh.util.constants.LoggerMessages.USER_WAS_NOT_FOUND;

@Slf4j
@Service
public class ForgotPasswordService {
    private final IPostgresRepo iPostgresRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ForgotPasswordService(IPostgresRepo iPostgresRepo, PasswordEncoder passwordEncoder) {
        this.iPostgresRepo = iPostgresRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void restorePass(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User updateUser = iPostgresRepo.findUserByLogin(user.getLogin()).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, user.getLogin())));
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info(String.format(SUCCESSFULLY_CHANGE_PASSWORD, user.getLogin()));
    }
}


