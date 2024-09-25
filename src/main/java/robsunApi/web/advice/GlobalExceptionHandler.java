package robsunApi.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access - Invalid token.");
        } 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request - " + ex.getMessage());
    }
}
