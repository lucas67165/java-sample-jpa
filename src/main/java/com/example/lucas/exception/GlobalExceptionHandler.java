package com.example.lucas.exception;

import com.example.lucas.data.enums.ResultEnum;
import com.example.lucas.data.response.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountExpiredException;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * Handles different types of exceptions and provides structured responses.
 *
 * @author Lucas
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Result<ErrorResponse>> handleApiException(ApiException e) {
		return ResponseEntity
				.status(e.getStatus()).body(Result.error(e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Result<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> validationErrors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationErrors.put(fieldName, errorMessage);
		});

		ErrorResponse errorResponse = new ErrorResponse(validationErrors);
		return ResponseEntity.badRequest().body(Result.error(ResultEnum.VALIDATION_FAILED, errorResponse));
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<Result<ErrorResponse>> handleBindException(BindException ex) {
		Map<String, String> validationErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			String fieldName = error.getField();
			String errorMessage = error.getDefaultMessage();
			validationErrors.put(fieldName, errorMessage);
		});
		ErrorResponse errorResponse = new ErrorResponse(validationErrors);
		return ResponseEntity.badRequest().body(Result.error(ResultEnum.VALIDATION_FAILED, errorResponse));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Result<ErrorResponse>> handleCustomValidationException(ValidationException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getValidationErrors());
		return ResponseEntity.badRequest().body(Result.error(ResultEnum.VALIDATION_FAILED, errorResponse));
	}

	@ExceptionHandler(AccountExpiredException.class)
	public ResponseEntity<Result<?>> handleAccountExpiredException(AccountExpiredException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(Result.error("User account has expired"));
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Result<Void>> handleGenericException(Exception ex) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error(ResultEnum.ERROR));
//	}
}
