package com.example.lucas.data.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author : Lucas
 * date:   2025-02-28 10:27
 * @description:
 */
@Data
public class UserCreateRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    private String name;
    private String nameEn;

    // Optional: set default values for booleans
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean approved = true;

    private Integer gender;
    private LocalDate dob;
    private String idCardNo;
    private LocalDate idCardIssueDate;
    private Integer maritalStatus;
    private String photo;
    private Integer userType;
    private String createdBy;
    private String updatedBy;

    // Roles
    private Set<Long> roleIds;
}
