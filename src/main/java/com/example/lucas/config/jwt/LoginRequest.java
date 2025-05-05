package com.example.lucas.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lucas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	private String username;
	private String password;
}
