package com.pharma.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.model.UserEntity;
import com.pharma.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public Optional<UserEntity> findByUsername(String username) {
		
		return repo.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		
		return repo.existsByUsername(username);
	}

}
