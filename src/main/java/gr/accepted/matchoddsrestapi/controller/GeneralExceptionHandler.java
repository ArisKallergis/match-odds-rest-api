package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.exception.EntityNotFoundException;
import gr.accepted.matchoddsrestapi.exception.IdMismatchException;
import gr.accepted.matchoddsrestapi.model.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IdMismatchException.class})
    protected ResponseEntity<?> handleIdMismatchException(IdMismatchException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(
                        e.getMessage(),
                        e.getErrorCode(),
                        request.getRequestURI(),
                        null
                ));

    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<?> handleEntityNoFoundException(EntityNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(
                        e.getMessage(),
                        e.getErrorCode(),
                        request.getRequestURI(),
                        null
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> validationErrors = e.getBindingResult().getFieldErrors()
                .stream().map((fieldError) -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(
                        "Invalid Input",
                        "invalidInput",
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        validationErrors
                ));
    }
}

