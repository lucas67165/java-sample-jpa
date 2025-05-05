package com.example.lucas.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Lucas
 */
@Data
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
	private final HttpStatus status;
	private final String message;
}
