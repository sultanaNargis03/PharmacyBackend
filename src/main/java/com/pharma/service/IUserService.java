package com.pharma.service;

import java.util.Optional;

import com.pharma.model.UserEntity;

public interface IUserService 
{
	Optional<UserEntity> findByUsername(String username);
	Boolean existsByUsername(String username);
}
