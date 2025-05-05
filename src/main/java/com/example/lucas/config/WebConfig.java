package com.example.lucas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:5666", "http://192.168.2.1:5666")
				.allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(true)
				.exposedHeaders("Authorization");
	}
}
