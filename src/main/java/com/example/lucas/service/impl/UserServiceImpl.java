package com.example.lucas.service.impl;

import com.example.lucas.config.security.AuthUser;
import com.example.lucas.config.security.UserService;
import com.example.lucas.data.filter.UserFilter;
import com.example.lucas.entity.Role;
import com.example.lucas.entity.User;
import com.example.lucas.exception.ApiException;
import com.example.lucas.exception.ValidationException;
import com.example.lucas.repository.RoleRepository;
import com.example.lucas.repository.UserRepository;
import com.example.lucas.spec.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lucas
 */
@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Override
	public Optional<AuthUser> findUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Bad credentials"));

		// Check expirationDate: if null or after today, the account is non-expired
		boolean accountNonExpired = user.getExpirationDate() == null || user.getExpirationDate().isAfter(LocalDate.now());

		AuthUser authUser = AuthUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(getAuthorities(user.getRoles()))
				.accountNonExpired(accountNonExpired)
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.expirationDate(user.getExpirationDate())
				.build();
		return Optional.of(authUser);
	}

	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
				Set<SimpleGrantedAuthority> authorities1 = roles.stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
					.collect(Collectors.toSet());

		Set<SimpleGrantedAuthority> authorities = roles.stream()
						.flatMap(role ->toStream(role))
						.collect(Collectors.toSet());
				authorities.addAll(authorities1);
				return authorities;
	}

	private Stream<SimpleGrantedAuthority> toStream(Role role){
		return role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}

	@Override
	public List<AuthUser> findAllUsers() {
		return userRepository.findAll().stream().map(user -> AuthUser.builder().username(user.getUsername()).password(user.getPassword()).authorities(getAuthorities(user.getRoles())).accountNonExpired(user.isAccountNonExpired()).accountNonLocked(user.isAccountNonLocked()).credentialsNonExpired(user.isCredentialsNonExpired()).enabled(user.isEnabled()).build()).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User create(User user, Set<Long> roleIds) {
		Map<String, String> validationErrors = new HashMap<>();

		if (userRepository.existsByUsername(user.getUsername())) {
			validationErrors.put("username", "Username already exists");
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			validationErrors.put("email", "Email already exists");
		}

		if (!validationErrors.isEmpty()) {
			throw new ValidationException(validationErrors);
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Persist the user first to ensure its persistence context is ready
		User savedUser = userRepository.save(user);

		// Assign roles using the join entity helper
		String modelType = "com.example.lucas.entity.User";
		if (roleIds != null && !roleIds.isEmpty()) {
			Set<Role> roles = roleRepository.findByIdIn(roleIds);
			for (Role role : roles) {
				savedUser.addRole(role, modelType);
			}
			// Save again to persist the join entities
			savedUser = userRepository.save(savedUser);
		}
		return savedUser;
	}

	@Override
	public Page<User> getUsers(UserFilter filter) {
		Sort sort = Sort.by(
				Sort.Direction.fromString(filter.getDirection()),
				filter.getSortBy()
		);
		Pageable pageable = PageRequest.of(filter.getPageNumber() - 1, filter.getPageSize(), sort);
		UserSpec spec = new UserSpec(filter);
		return userRepository.findAll(spec, pageable);
	}


	@Override
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
	}

	@Override
	public User update(User user) {
		return null;
	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public Long getCurrentUserId() {
		AuthUser userDetails = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
		return user.getId();
	}

}
