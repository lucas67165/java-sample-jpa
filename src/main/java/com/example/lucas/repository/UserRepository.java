package com.example.lucas.repository;

import com.example.lucas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Lucas
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
	
}
