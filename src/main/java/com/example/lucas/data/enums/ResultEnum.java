package com.example.lucas.data.enums;

import lombok.Getter;

/**
 * @author : Lucas
 * date:   2024-11-12 18:43
 * @description:
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "Operation successful"),
    ERROR(1, "An error occurred"),
    VALIDATION_FAILED(2, "Validation failed"),
    CREATED_SUCCESS(3, "Created successfully"),
    RETRIEVED_SUCCESS(4, "Retrieved successfully"),
    UPDATED_SUCCESS(5, "Updated successfully"),
    DELETED_SUCCESS(6, "Deleted successfully");

    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
