package bruh.services;

import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.repo.IUserRepo;
import bruh.services.authreg.AuthorizationService;
import bruh.util.encoder.PasswordEncoder;
import bruh.util.jwtproducer.JWTProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.stream.Stream;
import static bruh.util.constants.LoggerMessages.USER_HAS_ANOTHER_PASSWORD;
import static bruh.util.constants.LoggerMessages.USER_WAS_NOT_FOUND;

class AuthorizationServiceTest {

    private static final String USER = "user";
    private final IUserRepo postgressRepo = Mockito.mock(IUserRepo.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private final JWTProducer jwtProducer = Mockito.mock(JWTProducer.class);
    private final AuthorizationService cut = new AuthorizationService(postgressRepo, passwordEncoder, jwtProducer);

    private static Stream<Arguments> redisServiceTestNominal() {
        return Stream.of(
                Arguments.arguments(new User("jeid", "qwerty", USER)),
                Arguments.arguments(new User("ufora", "asdfgh", USER)),
                Arguments.arguments(new User("trolan12", "123456", USER))
        );
    }

    @ParameterizedTest
    @MethodSource("redisServiceTestNominal")
    void authorizeUserTest(User user) {
        Mockito.when(postgressRepo.findUserByLogin(user.getLogin())).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        Mockito.when(jwtProducer.createJWT(user.getLogin(), user.getRole())).thenReturn(user.getLogin());

        String expected = user.getLogin();
        String actual = cut.authorizeUser(user);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorizeUserNotFoundExceptionTest() {
        User user = new User("ufora2", "trolan2", USER);
        Mockito.when(postgressRepo.findUserByLogin(user.getLogin())).thenReturn(Optional.empty());

        Throwable throwable =
                Assertions.assertThrows(InvalidCredentialsException.class, () -> cut.authorizeUser(user));
        String expected = String.format(USER_WAS_NOT_FOUND, user.getLogin());
        String actual = throwable.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorizeUserIncorrectCredentialsExceptionTest() {
        User user = new User("ufora1", "trolan1", USER);
        User user1 = new User("ufora12", "trolan23", USER);
        Mockito.when(postgressRepo.findUserByLogin(user.getLogin())).thenReturn(Optional.of(user1));
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword() + "not");

        Throwable throwable =
                Assertions.assertThrows(InvalidCredentialsException.class, () -> cut.authorizeUser(user));
        String expected = String.format(USER_HAS_ANOTHER_PASSWORD, user.getLogin());
        String actual = throwable.getMessage();
        Assertions.assertEquals(expected, actual);
    }

}