package com.example.lucas.data.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author : Lucas
 * date:   2025-03-11 15:55
 * @description:
 */
@Data
public class RoleDTO {
    private Long id;
    private String name;
    private Set<PermissionDTO> permissions;
}
