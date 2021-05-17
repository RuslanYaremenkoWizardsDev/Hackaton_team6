package bruh.util.powergenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class PowerGenerator {
    private final Random random;

    @Autowired
    public PowerGenerator(Random random) {
        this.random = random;
    }

    public int generateRandomPower() {
        return random.nextInt(1000);
    }
}
