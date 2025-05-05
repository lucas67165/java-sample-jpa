package com.example.lucas.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Lucas
 */
public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return Optional.ofNullable(username);
	}

}
