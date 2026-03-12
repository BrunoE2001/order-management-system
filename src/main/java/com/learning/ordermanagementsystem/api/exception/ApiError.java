package com.learning.ordermanagementsystem.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/***
 * ApiError
 * Class to represent an API error response.
 */
@Getter
@Setter
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<ApiValidationError> errors;

    public ApiError(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
    }
}
