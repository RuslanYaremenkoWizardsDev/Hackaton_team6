package bruh.exceptions;

public class TournamentNotFoundException extends RuntimeException {
    public TournamentNotFoundException(String message) {
        super(message);
    }
}
