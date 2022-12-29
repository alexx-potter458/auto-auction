package pottertech.autoauctions.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler({UserException.class})
    public ResponseEntity handle(UserException e) {
        return ResponseEntity.ok().body(e.getMessage());
    }
}