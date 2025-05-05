package com.example.lucas.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Lucas
 */
public class ResourceNotFoundException extends ApiException{
	
	public ResourceNotFoundException(String resourceName, Long id) {
		super(HttpStatus.NOT_FOUND, String.format("%s With id = %d not found",resourceName,id ));
	}

}
