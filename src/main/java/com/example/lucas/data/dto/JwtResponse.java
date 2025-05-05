package com.example.lucas.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Lucas
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
	private String jwt;
	private String username;
	private Set<String> roles;
}
