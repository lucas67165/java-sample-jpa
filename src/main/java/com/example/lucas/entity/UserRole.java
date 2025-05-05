package com.example.lucas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Join entity to represent the relationship between User and Role,
 * including the extra column 'model_type' in the composite key.
 */
@Entity
@Getter
@Setter
@Table(name = "model_has_roles")
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "model_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
}
