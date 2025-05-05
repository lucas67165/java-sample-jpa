package com.example.lucas.exception;

import lombok.Getter;

import java.util.Map;

/**
 * @author : Lucas
 * date:   2024-11-13 16:09
 * @description:
 */
@Getter
public class ValidationException extends RuntimeException {
    private final Map<String, String> validationErrors;

    public ValidationException(Map<String, String> validationErrors) {
        super("Validation failed");
        this.validationErrors = validationErrors;
    }
}
