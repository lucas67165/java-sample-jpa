package com.example.lucas.service;


import com.example.lucas.config.jwt.LoginRequest;
import com.example.lucas.data.request.SignupRequest;

/**
 * @author Lucas
 */
public interface AuthService {
	String createUser(SignupRequest signUpRequest);
	
	String authenticateUser(LoginRequest loginRequest);
}
