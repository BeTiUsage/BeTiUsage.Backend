package test.betiusage.errorhandeling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.betiusage.errorhandeling.exception.ValidationException;
import test.betiusage.errorhandeling.exception.NotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles all exceptions that are not explicitly handled by other methods.
     * @return ResponseEntity with status 500 and a message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

    /**
     * This method handles ValidationExceptions.
     * @return ResponseEntity with status 400 and a message
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * This method handles NotFoundException.
     * @return ResponseEntity with status 404 and a message
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}