package bruh.util.encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class PasswordEncoder {

    private final MessageDigest messageDigest;

    @Value("${salt}")
    private String salt;

    @Autowired
    public PasswordEncoder(MessageDigest messageDigest) {
        this.messageDigest = messageDigest;
    }

    public String encode(String passwordToHash) {
        String generatedPassword;
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = messageDigest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }
}
