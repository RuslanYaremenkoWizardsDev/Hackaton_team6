package bruh.services;

import bruh.entity.User;
import bruh.repo.IUserRepo;
import bruh.services.authreg.RegistrationService;
import bruh.util.encoder.PasswordEncoder;
import bruh.util.powergenerator.PowerGenerator;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import java.sql.SQLException;
import java.util.stream.Stream;
import static bruh.util.constants.LoggerMessages.USERNAME_IS_BUSY;

class RegistrationServiceTest {
    private static final String USER = "user";
    private final IUserRepo postgresRepo = Mockito.mock(IUserRepo.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private final PowerGenerator powerGenerator = Mockito.mock(PowerGenerator.class);
    private final RegistrationService cut = new RegistrationService(postgresRepo, passwordEncoder, powerGenerator);

    private static Stream<Arguments> redisServiceTestNominal() {
        return Stream.of(
                Arguments.arguments(new User("jeid", "qwerty", USER)),
                Arguments.arguments(new User("ufora", "asdfgh", USER)),
                Arguments.arguments(new User("trolan12", "123456", USER))
        );
    }

    @ParameterizedTest
    @MethodSource("redisServiceTestNominal")
    void registerUserTest(User user) {
        Mockito.when(postgresRepo.save(user)).thenReturn(user);

        Assertions.assertDoesNotThrow(() -> cut.registerUser(user));
    }

    @Test
    void registerUserAlreadyExistsTest() {
        User user = new User("ufora3", "trolan3", USER);
        Mockito.when(postgresRepo.save(user)).thenThrow(new ConstraintViolationException(
                String.format(USERNAME_IS_BUSY, user.getLogin()),
                new SQLException(String.format(USERNAME_IS_BUSY, user.getLogin())),
                String.format(USERNAME_IS_BUSY, user.getLogin())));

        Throwable throwable =
                Assertions.assertThrows(ConstraintViolationException.class, () -> cut.registerUser(user));
        String expected = String.format(USERNAME_IS_BUSY, user.getLogin());
        String actual = throwable.getMessage();
        Assertions.assertEquals(expected, actual);
    }

}