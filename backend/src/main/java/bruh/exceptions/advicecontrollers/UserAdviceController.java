package bruh.exceptions.advicecontrollers;

import bruh.controllers.AuthorizationController;
import bruh.controllers.RegistrationController;
import bruh.exceptions.IncorrectUserFieldsException;
import bruh.exceptions.InvalidCredentialsException;
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
@ControllerAdvice(basePackageClasses = {AuthorizationController.class, RegistrationController.class})
public class UserAdviceController {

    @ExceptionHandler(value = IncorrectUserFieldsException.class)
    public ResponseEntity<Object> handleIncorrectUserFieldsException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
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
