package bruh.exceptions;

public class UserIsAlreadyInTournamentException extends RuntimeException{
    public UserIsAlreadyInTournamentException(String message) {
        super(message);
    }
}
