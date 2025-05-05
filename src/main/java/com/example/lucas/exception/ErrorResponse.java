package com.example.lucas.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * ErrorResponse
 *
 * @author Lucas
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
	private Map<String, String> validationErrors;
}
