package com.example.lucas.service.impl;


import com.example.lucas.config.jwt.LoginRequest;
import com.example.lucas.config.security.AuthUser;
import com.example.lucas.config.security.JwtUtils;
import com.example.lucas.data.request.SignupRequest;
import com.example.lucas.entity.Role;
import com.example.lucas.entity.User;
import com.example.lucas.exception.ApiException;
import com.example.lucas.repository.RoleRepository;
import com.example.lucas.repository.UserRepository;
import com.example.lucas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Lucas
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Value("${app.jwtExpirationMs}")
    private long jwtExpirationMs;

    @Override
    public String createUser(SignupRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        String defaultEmail = username + "@example.com";

        if (userRepository.existsByUsername(username)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        if (userRepository.existsByEmail(defaultEmail)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already taken!");
        }

        // Create new user's account with the default email
        User user = new User(username, defaultEmail,
                passwordEncoder.encode(signUpRequest.getPassword()));

        // Calculate expiration date from the config value (convert ms to days)
        long days = jwtExpirationMs / (24 * 60 * 60 * 1000);
        user.setExpirationDate(LocalDate.now().plusDays(days));

        // Persist the user to initialize the persistence context
        User savedUser = userRepository.save(user);

        // Assign the default role "merchant" using your join entity helper
        Role merchantRole = roleRepository.findByName("merchant")
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Default role 'merchant' is not found!"));
        String modelType = "com.example.lucas.entity.User";
        savedUser.addRole(merchantRole, modelType);

        // Save again to persist the join entities
        savedUser = userRepository.save(savedUser);

        return jwtUtils.generateJwtToken(username, savedUser.getExpirationDate());
    }

    @Override
    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser userPrincipal = (AuthUser) authentication.getPrincipal();

        return jwtUtils.generateJwtToken(userPrincipal.getUsername(), userPrincipal.getExpirationDate());
    }

}
