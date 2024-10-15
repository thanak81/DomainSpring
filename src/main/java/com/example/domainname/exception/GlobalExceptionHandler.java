package com.example.domainname.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("dateTime", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problemDetail.setTitle("Bad request");
        problemDetail.setProperty("dateTime", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        for (var fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad request");
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetail);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(ConflictException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }


    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException ex) {

        Map<String, String> errors = new HashMap<>();
        for (var parameterError : ex.getAllValidationResults()) {
            String parameterName = parameterError.getMethodParameter().getParameterName();
            for (var error : parameterError.getResolvableErrors()) {
                errors.put(parameterName, error.getDefaultMessage());
            }
        }

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

}
