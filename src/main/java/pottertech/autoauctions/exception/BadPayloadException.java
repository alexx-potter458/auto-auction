package pottertech.autoauctions.exception;

public class BadPayloadException extends RuntimeException {
    public BadPayloadException(String message){
        super(message);
    }
}
