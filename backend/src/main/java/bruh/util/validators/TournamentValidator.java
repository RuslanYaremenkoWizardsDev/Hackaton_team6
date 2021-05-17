package bruh.util.validators;

import bruh.exceptions.InvalidParticipantsNumberException;
import org.springframework.stereotype.Component;
import static bruh.util.constants.LoggerMessages.UNACCEPTABLE_VALUE_FOR_PARTICIPANTS;

@Component
public class TournamentValidator {

    public int validateParticipants(int participantsDto) {
        int i = 128;
        int counter = 0;
        while (i / 2 != 0) {
            counter++;
            int checkNumber = i / 2;
            if (participantsDto == checkNumber) {
                return counter;
            }
            i /= 2;
        }
        throw new InvalidParticipantsNumberException(
                String.format(UNACCEPTABLE_VALUE_FOR_PARTICIPANTS, participantsDto));
    }
}
