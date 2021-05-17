package bruh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.Random;

@Configuration
public class BeanConfiguration {
    private static final String MESSAGE_DIGEST_ALGORITHM = "SHA-512";

    @Bean
    public Clock getClock() {
        return Clock.systemUTC();
    }

    @Bean
    public MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }
}
