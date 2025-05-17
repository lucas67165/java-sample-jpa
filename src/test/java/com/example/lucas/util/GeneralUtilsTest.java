package com.example.lucas.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneralUtilsTest {

	@Test
	public void showPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("123456");
		System.out.println(encode);
	}
}
