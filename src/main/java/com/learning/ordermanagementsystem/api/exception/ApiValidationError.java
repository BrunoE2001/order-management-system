package com.learning.ordermanagementsystem.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiValidationError {
    private String field;
    private String message;
}
