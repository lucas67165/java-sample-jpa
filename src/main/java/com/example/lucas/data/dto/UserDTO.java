package com.example.lucas.data.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author : Lucas
 * date:   2025-02-28 10:21
 * @description:
 */
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String nameEn;
    private String username;
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Integer gender;
    private LocalDate dob;
    private String idCardNo;
    private LocalDate idCardIssueDate;
    private Integer maritalStatus;
    private String photo;
    private Integer userType;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<RoleDTO> roles;
}
