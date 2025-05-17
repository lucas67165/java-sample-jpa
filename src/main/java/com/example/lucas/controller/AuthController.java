package com.example.lucas.controller;

import com.example.lucas.config.jwt.LoginRequest;
import com.example.lucas.config.security.AuthUser;
import com.example.lucas.data.request.SignupRequest;
import com.example.lucas.data.response.AuthResponse;
import com.example.lucas.data.response.Result;
import com.example.lucas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for handling authentication-related endpoints
 * 
 * @author Lucas
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Authenticates a user and returns a JWT token
     *
     * @param loginRequest the request containing user login credentials
     * @return ResponseEntity with the authentication result and JWT token
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Authenticate user and generate JWT
        String jwt = authService.authenticateUser(loginRequest);

        // Retrieve user details from SecurityContext
        AuthUser userDetails = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Build AuthResponse with the necessary info
        AuthResponse responseBody = new AuthResponse(
                userDetails.getUsername(),
                userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()),
                jwt
        );

        // Wrap the response body
        return ResponseEntity.ok().body(Result.ok(responseBody));
    }

    /**
     * Registers a new user and returns a JWT token
     *
     * @param signUpRequest the request containing user registration information
     * @return ResponseEntity with the registration result and JWT token
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        String jwt = authService.createUser(signUpRequest);

        // Build the AuthResponse (you might want to include actual assigned roles)
        AuthResponse responseBody = new AuthResponse(
                signUpRequest.getUsername(),
                List.of("ROLE_merchant"),
                jwt
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(Result.ok(responseBody));
    }


    /**
     * Retrieves the access codes (authorities) for the authenticated user
     *
     * @return ResponseEntity with the list of authorities
     */
    @GetMapping("/codes")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAccessCodes() {
        AuthUser userDetails = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(Result.ok(authorities));
    }

}
