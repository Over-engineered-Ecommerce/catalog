package com.overengineeredecommerce.transport.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerHandler {
    private static final String DESCRIPTION = "description";

    @ExceptionHandler({BindException.class, HttpMessageNotReadableException.class,
            IllegalArgumentException.class, MethodArgumentTypeMismatchException.class,
    })
    private ResponseEntity<Map<String, String>> handleIllegalArgumentException(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(mountError(ex));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    private ResponseEntity<Map<String, String>> notFound(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(mountError(ex));
    }

    @ExceptionHandler(value = UniqueInsertConstraint.class)
    private ResponseEntity<Map<String, String>> duplication(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mountError(ex));
    }

    @ExceptionHandler(value = InternalError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<Map<String, String>> serverError(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(mountError(ex));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        Collectors
                                .toMap(e -> e.getField(), e -> e.getDefaultMessage(),
                                        (existing, replacement) -> existing, HashMap::new));
        errors.put("time", LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(errors);
    }



    private Map<String, String> mountError(Exception e) {
        return Map.of(DESCRIPTION, e.getMessage(),
                "time", LocalDateTime.now().toString());
    }
}