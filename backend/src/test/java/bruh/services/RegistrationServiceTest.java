package bruh.services;

import bruh.entity.User;
import bruh.repo.IPostgresRepo;
import bruh.util.encoder.PasswordEncoder;
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
    private final IPostgresRepo postgresRepo = Mockito.mock(IPostgresRepo.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private final RegistrationService cut = new RegistrationService(postgresRepo, passwordEncoder);

    private static Stream<Arguments> redisServiceTestNominal() {
        return Stream.of(
                Arguments.arguments(new User("jeid", "qwerty","user")),
                Arguments.arguments(new User("ufora", "asdfgh", "user")),
                Arguments.arguments(new User("trolan12", "123456", "user"))
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
        User user = new User("ufora3", "trolan3", "user");
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