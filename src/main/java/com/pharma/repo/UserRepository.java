package com.pharma.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);
	Boolean existsByUsername(String username);
}
