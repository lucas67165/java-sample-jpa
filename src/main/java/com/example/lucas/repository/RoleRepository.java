package com.example.lucas.repository;

import com.example.lucas.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Lucas
 */
public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String username);

	Set<Role> findByIdIn(Set<Long> ids);
	
}
