package com.pharma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.model.Roles;
import com.pharma.repo.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleRepository repo;
	
	@Override
	public Roles createNewRole(Roles role) {
		
		return repo.save(role);
	}
	
}
