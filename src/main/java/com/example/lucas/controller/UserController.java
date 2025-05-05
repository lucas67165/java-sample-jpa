package com.example.lucas.controller;

import com.example.lucas.config.security.UserService;
import com.example.lucas.data.dto.PageDTO;
import com.example.lucas.data.dto.UserDTO;
import com.example.lucas.data.filter.UserFilter;
import com.example.lucas.data.request.UserCreateRequest;
import com.example.lucas.data.response.Result;
import com.example.lucas.entity.User;
import com.example.lucas.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * User Controller
 *
 * @author Lucas
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('add-user') or hasRole('developer') or hasRole('admin')")
    public ResponseEntity<Result<UserDTO>> create(@Valid @RequestBody UserCreateRequest request) {
        User user = UserMapper.INSTANCE.toUser(request);
        user = userService.create(user, request.getRoleIds());

        UserDTO userDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(Result.ok(userDTO));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view-user') or hasRole('developer') or hasRole('admin')")
    public ResponseEntity<Result<PageDTO>> list(@Validated @ModelAttribute UserFilter userFilter) {
        Page<User> page = userService.getUsers(userFilter);
        Page<UserDTO> dtoPage = page.map(UserMapper.INSTANCE::toUserDTO);
        PageDTO pageDTO = new PageDTO(dtoPage);
        return ResponseEntity.ok(Result.ok(pageDTO));
    }

    @GetMapping("/info")
    public ResponseEntity<Result<UserDTO>> getCurrentUserInfo() {
        Long userId = userService.getCurrentUserId();
        User user = userService.findById(userId);
        UserDTO userDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.ok(Result.ok(userDTO));
    }

}
