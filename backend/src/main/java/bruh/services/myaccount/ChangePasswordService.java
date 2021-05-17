package bruh.services.myaccount;

import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.repo.IUserRepo;
import bruh.util.encoder.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static bruh.util.constants.LoggerMessages.*;

@Slf4j
@Service
public class ChangePasswordService {

    private final IUserRepo iPostgresRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ChangePasswordService(IUserRepo iPostgresRepo, PasswordEncoder passwordEncoder) {
        this.iPostgresRepo = iPostgresRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void changePass(String login, String password, String newPassword) {
        password = passwordEncoder.encode(password);
        newPassword = passwordEncoder.encode(newPassword);
        User updateUser = iPostgresRepo.findUserByLogin(login).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, login)));
        if (password.equals(updateUser.getPassword())) {
            updateUser.setPassword(passwordEncoder.encode(newPassword));
            log.info(String.format(SUCCESSFULLY_CHANGE_PASSWORD, login));
        }
    }
}
