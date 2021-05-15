package bruh.util.jwtproducer;

import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import java.time.Clock;
import java.util.stream.Stream;

class JWTProducerTest {
    private final Clock clock = Mockito.mock(Clock.class);
    private final long expTime = 4230000000000000L;

    JWTProducer cut = new JWTProducer(clock);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(cut, "secretWord", "766d42ecaf3218697be7953f");
        ReflectionTestUtils.setField(cut, "expTime", expTime);
    }

    public static Stream<Arguments> createJWTTestNominal() {
        return Stream.of(
                Arguments.arguments("trolan", "user", 215151155, 215151),
                Arguments.arguments("ufora", "admin", 123465456, 123465),
                Arguments.arguments("vurado", "user", 12456214, 12456),
                Arguments.arguments("jeid", "admin", 854123654, 854123)

        );
    }

    @ParameterizedTest
    @MethodSource("createJWTTestNominal")
    void createJWTTest(String login, String role, long currentTime, long expectedTime) {
        long expectedExpirationTime = expTime / 1000 + expectedTime;
        Mockito.when(clock.millis()).thenReturn(currentTime);

        String actual = Base64Codec.BASE64.decodeToString(cut.createJWT(login, role));

        Assertions.assertTrue(actual.contains(login));
        Assertions.assertTrue(actual.contains(role));
        Assertions.assertTrue(actual.contains(String.valueOf(expectedExpirationTime)));
        Assertions.assertTrue(actual.contains(String.valueOf(expectedTime)));
    }
}