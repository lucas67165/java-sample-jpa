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
 * @author Lucas
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

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
