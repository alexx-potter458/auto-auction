package pottertech.autoauctions.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler({UserException.class})
    public ResponseEntity handle(UserException e) {
        return ResponseEntity.ok().body(e.getMessage());
    }

    @ExceptionHandler({CarException.class})
    public ResponseEntity handle(CarException e) {
        return ResponseEntity.ok().body(e.getMessage());
    }

    @ExceptionHandler({ReportException.class})
    public ResponseEntity handle(ReportException e) {
        return ResponseEntity.ok().body(e.getMessage());
    }

    @ExceptionHandler({BadPayloadException.class})
    public ResponseEntity handle(BadPayloadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}