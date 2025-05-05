package com.example.lucas.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite key for UserRole.
 */
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
    private String modelType;
}
