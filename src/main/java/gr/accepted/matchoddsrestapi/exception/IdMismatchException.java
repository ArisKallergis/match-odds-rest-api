package gr.accepted.matchoddsrestapi.exception;

public class IdMismatchException extends RuntimeException {

    public IdMismatchException(String message) {
        super(message);
    }

    public IdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
