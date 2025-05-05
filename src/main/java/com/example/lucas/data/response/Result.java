package com.example.lucas.data.response;

import com.example.lucas.data.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response wrapper for API results, including code, message, and optional data.
 * Provides factory methods for various success and error responses.
 *
 * @author Lucas
 * @param <T> the type of the response data
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    // Constructor for responses without data
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    // Factory method for success with data, using default success message
    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    // Factory method for success with enum message and data
    public static <T> Result<T> ok(ResultEnum messageEnum, T data) {
        return new Result<>(messageEnum.getCode(), messageEnum.getMessage(), data);
    }

    // Factory method for success with custom message and data
    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    // Factory method for success without data and with default success message
    public static <T> Result<T> ok() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    // Factory method for success without data and with a custom message
    public static <T> Result<T> ok(String message) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, null);
    }

    // Factory method for error with enum message
    public static <T> Result<T> error(ResultEnum messageEnum) {
        return new Result<>(messageEnum.getCode(), messageEnum.getMessage());
    }

    // Factory method for error with custom message
    public static <T> Result<T> error(String message) {
        return new Result<>(ResultEnum.ERROR.getCode(), message);
    }

    // Factory method for error with enum message and data
    public static <T> Result<T> error(ResultEnum messageEnum, T data) {
        return new Result<>(messageEnum.getCode(), messageEnum.getMessage(), data);
    }

    // Factory method for error with custom message and data
    public static <T> Result<T> error(String message, T data) {
        return new Result<>(ResultEnum.ERROR.getCode(), message, data);
    }
}