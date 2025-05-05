package com.example.lucas.mapper;

import com.example.lucas.data.dto.PermissionDTO;
import com.example.lucas.data.dto.RoleDTO;
import com.example.lucas.data.dto.UserDTO;
import com.example.lucas.data.request.UserCreateRequest;
import com.example.lucas.data.request.UserUpdateRequest;
import com.example.lucas.entity.Permission;
import com.example.lucas.entity.Role;
import com.example.lucas.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Lucas
 * date:   2025-02-28 10:54
 * @description:
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserCreateRequest request);

    User toUser(UserUpdateRequest request);

    // Map roles with custom mapping logic
    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserDTO toUserDTO(User user);

    // Helper method to map roles set to RoleDTO set
    default Set<RoleDTO> mapRoles(Set<Role> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream().map(this::toRoleDTO).collect(Collectors.toSet());
    }

    // Helper method to map a single Role to RoleDTO
    default RoleDTO toRoleDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setPermissions(mapPermissions(role.getPermissions()));
        return roleDTO;
    }

    // Helper method to map permissions set to PermissionDTO set
    default Set<PermissionDTO> mapPermissions(Set<Permission> permissions) {
        if (permissions == null) {
            return Collections.emptySet();
        }
        return permissions.stream().map(permission -> {
            PermissionDTO dto = new PermissionDTO();
            dto.setId(permission.getId());
            dto.setName(permission.getName());
            return dto;
        }).collect(Collectors.toSet());
    }
}
