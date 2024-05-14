package com.pharma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.config.JWTGenerator;
import com.pharma.model.AuthResponseDTO;
import com.pharma.model.LoginDto;
import com.pharma.model.RegisterDto;
import com.pharma.model.Roles;
import com.pharma.model.UserEntity;
import com.pharma.repo.RoleRepository;
import com.pharma.repo.UserRepository;
import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	@Autowired
	private JWTGenerator jwtGenerator;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
	
	
	 @PostMapping("/register")
	    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) 
	 	{
	        if (userRepository.existsByUsername(registerDto.getUsername())) 
	        {
	            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
	        }
	        
	        UserEntity user = new UserEntity();
	        
	        user.setUsername(registerDto.getUsername());
	        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
	      //  System.out.println(registerDto.getPassword());
	        user.setEmail(registerDto.getEmail());
	        user.setPhnNo(registerDto.getPhnNo());
	        
	        Roles roles = roleRepository.findByName("USER").get();
	        user.setRoles(Collections.singletonList(roles));
	        //System.out.println(user.getRoles());
	        
	        userRepository.save(user);

	        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
	    }
}
