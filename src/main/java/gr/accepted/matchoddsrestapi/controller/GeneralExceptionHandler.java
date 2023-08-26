package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.exception.EntityNotFoundException;
import gr.accepted.matchoddsrestapi.exception.IdMismatchException;
import gr.accepted.matchoddsrestapi.model.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<?> handleEntityNoFoundException(ConstraintViolationException e, HttpServletRequest request) {
        List<String> constraintViolationErrors = e.getConstraintViolations()
                .stream().map((fieldError) -> fieldError.getPropertyPath() + ": " + fieldError.getMessage()).toList();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(
                        "Invalid Input",
                        "invalidInput",
                        request.getRequestURI(),
                        constraintViolationErrors
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(
                        ex.getMessage(),
                        "httpMessageNotReadable",
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        null
                ));
    }


}

