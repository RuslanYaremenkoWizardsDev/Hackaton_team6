package bruh.util.jwtproducer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.Clock;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTProducer {

    @Value("${secretWord}")
    private String secretWord;

    @Value("${expirationTime}")
    private long expTime;

    private final Clock clock;

    @Autowired
    public JWTProducer(Clock clock) {
        this.clock = clock;
    }

    @PostConstruct
    private void init() {
        secretWord = Base64.getEncoder().encodeToString(secretWord.getBytes());
    }

    public String createJWT(String login) {
        Date localDate = new Date(clock.millis());
        Date expDate = new Date(clock.millis() + expTime);
        Claims claims = Jwts.claims().setSubject(login);

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(localDate)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, secretWord)
                .compact();
    }
}
