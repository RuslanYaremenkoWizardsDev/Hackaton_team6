package bruh.services;

import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.repo.IPostgresRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import static bruh.util.constants.LoggerMessages.SUCCESSFULLY_CHANGE_LOGIN;
import static bruh.util.constants.LoggerMessages.USER_WAS_NOT_FOUND;

@Slf4j
@Service
public class ChangeSecretWordService {
    private final IPostgresRepo iPostgresRepo;

    @Autowired
    public ChangeSecretWordService(IPostgresRepo iPostgresRepo) {
        this.iPostgresRepo = iPostgresRepo;
    }

    @Transactional
    public void restoreSecretWord(String login, String secretWord, String newSecretWord) {
        User updateUser = iPostgresRepo.findUserByLogin(login).orElseThrow(()
                -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, login)));
        if (secretWord.equals(updateUser.getSecretWord())) {
            log.info(String.format(SUCCESSFULLY_CHANGE_LOGIN, login));
            updateUser.setSecretWord(newSecretWord);
        }
    }
}
