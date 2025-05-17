package com.example.lucas.service;

import com.example.lucas.config.jwt.LoginRequest;
import com.example.lucas.data.request.SignupRequest;

/**
 * Service interface for authentication operations
 * 
 * @author Lucas
 * @since 1.0
 */
public interface AuthService {
    /**
     * Creates a new user with the provided information
     * 
     * @param signUpRequest the request containing user registration information
     * @return JWT token for the newly created user
     */
    String createUser(SignupRequest signUpRequest);

    /**
     * Authenticates a user with the provided credentials
     * 
     * @param loginRequest the request containing user login credentials
     * @return JWT token for the authenticated user
     */
    String authenticateUser(LoginRequest loginRequest);
}
