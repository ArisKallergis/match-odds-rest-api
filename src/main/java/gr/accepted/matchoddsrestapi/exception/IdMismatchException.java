package gr.accepted.matchoddsrestapi.exception;

import lombok.Getter;

@Getter
public class IdMismatchException extends RuntimeException {

    private final String errorCode = "idMismatch";

    public IdMismatchException(String message) {
        super(message);
    }

    public IdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
