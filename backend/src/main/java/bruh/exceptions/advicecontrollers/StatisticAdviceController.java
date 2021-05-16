package bruh.exceptions.advicecontrollers;

import bruh.controllers.statistic.CreateTournamentStatisticController;
import bruh.controllers.statistic.GetTournamentStatisticController;
import bruh.controllers.tournament.CreateTournamentController;
import bruh.controllers.tournament.GetTournamentController;
import bruh.exceptions.IncorrectTournamentFieldsException;
import bruh.exceptions.InvalidParticipantsNumberException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static bruh.util.constants.EntityMessages.UNIQUE_LOGIN_CONSTRAINT;
import static bruh.util.constants.LoggerMessages.UNEXPECTED_ERROR;
import static bruh.util.constants.LoggerMessages.USERNAME_IS_BUSY;
import static bruh.util.constants.ResponseHeaders.APPLICATION_JSON_WITH_CHARSET;
import static bruh.util.constants.ResponseHeaders.HEADER_CONTENT_TYPE;

@Slf4j
@ControllerAdvice(basePackageClasses = {CreateTournamentStatisticController.class, GetTournamentStatisticController.class})
public class StatisticAdviceController {
    @ExceptionHandler(value = IncorrectTournamentFieldsException.class)
    public ResponseEntity<Object> handleIncorrectTournamentFieldsException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleSqlExceptionHelper(Exception e) {
        String cause = e.getCause().getCause().getLocalizedMessage();
        String message = String.format(USERNAME_IS_BUSY, cause.substring(cause.lastIndexOf("=") + 2,
                cause.lastIndexOf(")")));
        log.error(e.getMessage());
        if (e.getMessage().contains(UNIQUE_LOGIN_CONSTRAINT)) {
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

}
