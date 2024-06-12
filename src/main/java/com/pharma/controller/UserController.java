package com.pharma.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.model.UserEntity;
import com.pharma.service.UserServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:5173/")
@RequestMapping("/api-user")
public class UserController 
{
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping("/getbyusername/{username}")
	public ResponseEntity<Optional<UserEntity>> findByUsername(@PathVariable("username")String username) {
		
		return new ResponseEntity<>(service.findByUsername(username),HttpStatus.OK);
	}

	@GetMapping("/presentbyusername/{username}")
	public ResponseEntity< Boolean> existsByUsername(@PathVariable("username")String username) {
		
		return new ResponseEntity<>(service.existsByUsername(username),HttpStatus.OK);
	}
}
