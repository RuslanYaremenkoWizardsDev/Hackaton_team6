package bruh.exceptions.advicecontrollers;

import bruh.controllers.tournament.CreateTournamentController;
import bruh.controllers.tournament.GetTournamentController;
import bruh.exceptions.*;
import bruh.util.constants.LoggerMessages;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static bruh.util.constants.EntityMessages.UNIQUE_NAME_CONSTRAINT;
import static bruh.util.constants.LoggerMessages.*;
import static bruh.util.constants.ResponseHeaders.APPLICATION_JSON_WITH_CHARSET;
import static bruh.util.constants.ResponseHeaders.HEADER_CONTENT_TYPE;

@Slf4j
@ControllerAdvice(basePackageClasses = {CreateTournamentController.class, GetTournamentController.class})
public class TournamentAdviceController {

    @ExceptionHandler(value = InvalidParticipantsNumberException.class)
    public ResponseEntity<Object> handleInvalidParticipantsNumberException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }

    @ExceptionHandler(value = IncorrectParticipantsFieldsException.class)
    public ResponseEntity<Object> handleIncorrectParticipantsFieldsException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }

    @ExceptionHandler(value = TournamentNotFoundException.class)
    public ResponseEntity<Object> handleTournamentNotFoundException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }

    @ExceptionHandler(value = IncorrectTournamentFieldsException.class)
    public ResponseEntity<Object> handleIncorrectTournamentFieldsException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleSqlExceptionHelper(Exception e) {
        String cause = e.getCause().getCause().getLocalizedMessage();
        String message = String.format(TOURNAMENT_NAME_IS_BUSY, cause.substring(cause.lastIndexOf("=") + 2,
                cause.lastIndexOf(")")));
        log.error(e.getMessage());
        if (e.getMessage().contains(UNIQUE_NAME_CONSTRAINT)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                    .body(message);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                    .body(UNEXPECTED_ERROR);
        }
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<Object> handleInvalidCredentialsException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }
}
