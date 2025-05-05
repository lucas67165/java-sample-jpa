package com.example.lucas.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : Lucas
 * date:   2025-02-28 15:21
 * @description:
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private List<String> authorities;
    private String accessToken;
}
