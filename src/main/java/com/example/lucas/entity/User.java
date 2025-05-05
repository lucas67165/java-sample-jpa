package com.example.lucas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lucas
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "userRoles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private String nameEn;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, length = 100)
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    // Additional Info
    private Integer gender;
    private LocalDate dob;
    private String idCardNo;
    private LocalDate idCardIssueDate;

    private Integer maritalStatus;

    private String photo;
    private Integer userType;
    private String createdBy;
    private String updatedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDate expirationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UserRole> userRoles = new HashSet<>();

    // Convenience Constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.userRoles = new HashSet<>();
    }

    // Helper method to add a role with a specified modelType.
    public void addRole(Role role, String modelType) {
        UserRole userRole = new UserRole();
        UserRoleId userRoleId = new UserRoleId();
        // Set the userId from the persisted user
        userRoleId.setUserId(this.getId());
        userRoleId.setRoleId(role.getId());
        userRoleId.setModelType(modelType);
        userRole.setId(userRoleId);
        userRole.setUser(this);
        userRole.setRole(role);
        this.userRoles.add(userRole);
    }

    // Convenience getter to return roles directly
    @Transient
    public Set<Role> getRoles() {
        return userRoles != null
                ? userRoles.stream().map(UserRole::getRole).collect(Collectors.toSet())
                : Collections.emptySet();
    }
}
