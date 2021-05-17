package bruh.exceptions;

public class TournamentIsAlreadyFinishedException extends RuntimeException {
    public TournamentIsAlreadyFinishedException(String message) {
        super(message);
    }
}
