package bruh.services;

import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.repo.IPostgresRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static bruh.util.constants.LoggerMessages.*;

@Slf4j
@Service
public class ChangeLoginService {
    private final IPostgresRepo iPostgresRepo;

    public ChangeLoginService(IPostgresRepo iPostgresRepo) {
        this.iPostgresRepo = iPostgresRepo;
    }

    @Transactional
    public void toChangeLogin(String login, String newLogin) {
        User updateUser = iPostgresRepo.findUserByLogin(login).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, login)));
        if (login.equals(updateUser.getLogin())) {
            log.info(String.format(SUCCESSFULLY_CHANGE_LOGIN, login));
            updateUser.setLogin(newLogin);
        }
    }
}
