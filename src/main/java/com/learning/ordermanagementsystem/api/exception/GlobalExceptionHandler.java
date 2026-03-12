package com.learning.ordermanagementsystem.api.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /* --------------------------  VALIDATIONS DE SPRING-------------------------------------------------------------*/

    /***
     * handleConstraintViolation
     * -
     * Handle constraint violation exceptions
     * -
     * @param ex ConstraintViolationException
     * @return ApiError ResponseEntity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(
            ConstraintViolationException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation error"
        );

        List<ApiValidationError> validationErrors = ex.getConstraintViolations()
                .stream()
                .map(violation -> new ApiValidationError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .toList();

        error.setErrors(validationErrors);

        return ResponseEntity.badRequest().body(error);
    }

    /***
     * handleValidationErrors
     * -
     * Handle validation errors
     * Validated, Valid, Jakarta Validation
     * -
     * @param ex MethodArgumentNotValidException
     * @return ApiError ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<ApiValidationError> validationErrors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error ->
                                new ApiValidationError(
                                        error.getField(),
                                        error.getDefaultMessage()))
                        .toList();

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Validation Error");
        error.setErrors(validationErrors);

        return ResponseEntity.badRequest().body(error);
    }

    /***
     * handleMethodNotAllowed
     * -
     * Handle method not allowed exceptions
     * -
     * @param ex HttpRequestMethodNotSupportedException
     * @return ApiError ResponseEntity
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException ex) {

        ApiError error = new ApiError(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Method not allowed: " + ex.getMethod()
        );

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(error);
    }

    /***
     * handleRuntimeException
     * -
     * Handle runtime exceptions
     * -
     * @param ex RuntimeException
     * @return ApiError ResponseEntity
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
