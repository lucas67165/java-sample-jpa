package com.example.lucas.data.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : Lucas
 * date:   2025-02-28 10:27
 * @description:
 */
@Data
public class UserUpdateRequest extends UserCreateRequest {
    @NotNull
    private Long id;
}
