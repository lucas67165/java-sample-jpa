package com.example.lucas.config.security;

import com.example.lucas.data.filter.UserFilter;
import com.example.lucas.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Lucas
 */
public interface UserService {
    Optional<AuthUser> findUserByUsername(String username);

    List<AuthUser> findAllUsers();

    User create(User user, Set<Long> roleIds);

    Page<User> getUsers(UserFilter userFilter);

    User findById(Long id);

    User update(User user);

    void deleteById(Long id);

    Long getCurrentUserId();
}
