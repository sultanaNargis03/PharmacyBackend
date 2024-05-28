package com.pharma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.model.Roles;
import com.pharma.service.RoleServiceImpl;

@RestController
@RequestMapping("api-role")
public class RoleController
{
	@Autowired	
	RoleServiceImpl service;
	@PostMapping("/role")
	public Roles createNewRole(@RequestBody Roles role)
	{
		return service.createNewRole(role);
	}
}
