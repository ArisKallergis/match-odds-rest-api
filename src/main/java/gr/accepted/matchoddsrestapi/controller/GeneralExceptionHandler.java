package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.exception.EntityNotFoundException;
import gr.accepted.matchoddsrestapi.exception.IdMismatchException;
import gr.accepted.matchoddsrestapi.model.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IdMismatchException.class})
    protected ResponseEntity<Object> handleIdMismatchException(IdMismatchException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(
                        e.getMessage(),
                        e.getErrorCode(),
                        request.getRequestURI()
                ));

    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNoFoundException(EntityNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(
                        e.getMessage(),
                        e.getErrorCode(),
                        request.getRequestURI()
                ));
    }
}

