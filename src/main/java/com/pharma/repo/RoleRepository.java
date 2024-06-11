package com.pharma.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
	
	Optional<Roles> findByName(String name);
	
	
}
