package bruh.util.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.util.ReflectionTestUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

class PasswordEncoderTest {

    private final PasswordEncoder cut = new PasswordEncoder(MessageDigest.getInstance("SHA-512"));
    private final String salt = "10";

    PasswordEncoderTest() throws NoSuchAlgorithmException {
    }

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(cut, "salt", salt);
    }

    private static Stream<Arguments> encodeTestNominal() {
        return Stream.of(
                Arguments.arguments("werty",
                        "7426e1ef9300e07a98ce8712b33d482605b8fef6b1b4fb9f16e2d9ac3c1411c1320038a011a6b8aec22f3458055f8803f03066311d263a263e34f73324198c67"),
                Arguments.arguments("asdfgh",
                        "2f88f4905b0a6cfa0d204a54014494c52c0072863bf127584c5979f24b432756dc6a8d5b4c92c08c36228018c794f5f2f7ee03e91013f74a232e53a21aad3455"),
                Arguments.arguments("123456",
                        "8b86c141d8a90bd750e49b7d93e4ca896465ab328804d1e8f97b3346d8bcc28b93f1f6e4689db1082751d73c3ba6c032a1ba88c928aee83c3fce9e1e78234103")
        );
    }

    @ParameterizedTest
    @MethodSource("encodeTestNominal")
    void encodeTest(String password, String expected) {
        String actual = cut.encode(password);

        Assertions.assertEquals(expected, actual);
    }

}