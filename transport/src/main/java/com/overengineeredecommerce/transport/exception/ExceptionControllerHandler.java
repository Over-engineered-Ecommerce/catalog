package com.overengineeredecommerce.transport.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 Global exception handler for the application.

 This class handles various exceptions that might occur during request processing
 and provides appropriate error responses in a consistent format.
 It uses {@link RestControllerAdvice} to intercept exceptions across all controllers.

 */
@RestControllerAdvice
public class ExceptionControllerHandler {

    private static final String DESCRIPTION = "description";
    private static final String TIME = "time";


    @ExceptionHandler({BindException.class, HttpMessageNotReadableException.class,
            IllegalArgumentException.class, MethodArgumentTypeMismatchException.class,
    })
    private ResponseEntity<Map<String, String>> handleIllegalArgumentException(Exception e) {
        return ResponseEntity.badRequest().body(buildErrorResponse(e));
    }

    @ExceptionHandler( {NoHandlerFoundException.class, NotFound.class })
    private ResponseEntity<Map<String, String>> notFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(e));
    }

    @ExceptionHandler(value = UniqueInsertConstraint.class)
    private ResponseEntity<Map<String, String>> duplication(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildErrorResponse(e));
    }


    @ExceptionHandler(value = InternalError.class)
    private ResponseEntity<Map<String, String>> serverError(Exception e) {
        return ResponseEntity.badRequest().body(buildErrorResponse(e));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        Collectors
                                .toMap(e -> e.getField(), e -> e.getDefaultMessage(),
                                        (existing, replacement) -> existing, HashMap::new));
        errors.put(TIME, LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(errors);
    }


    /**
     Creates a standardized error response map containing the error description and timestamp.

     @param exception The exception to extract the error message from
     @return A map containing the error description and timestamp
     */
    private Map<String, String> buildErrorResponse(Exception exception) {
        return Map.of(
                TIME, LocalDateTime.now().toString(),
                DESCRIPTION, exception.getMessage()
        );
    }
}