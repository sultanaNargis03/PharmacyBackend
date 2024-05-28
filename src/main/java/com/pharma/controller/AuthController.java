package com.pharma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins="http://localhost:5173/")
@RestController
@RequestMapping("/api/auth")
@Tag(name="Pharmacy-auth",description="This API URL will help to authenticate and authorize users")
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
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	UserRepository userRepo;
	
	@Value("{spring.mail.username}")
	private String fromId;
		

	@Operation(summary="POST operation",description="API will accept json LoginDto and will authenticate the user to perform login operation")
	@PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        String token = jwtGenerator.generateToken(authentication);
       
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
	
	
	 @PostMapping("/register")
	 @Operation(summary="POST operation",description="API will accept and json RegisterDto and will register the user")
	    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) throws MessagingException 
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
	        Roles roles = roleRepository.findByName("ADMIN").get();
	        user.setRoles(Collections.singletonList(roles));
	        //System.out.println(user.getRoles());
	        
	        userRepository.save(user);
	        
	        String message="Assalamu alaikum "+registerDto.getUsername()+" You have been successfuly registered to Pharma App";
	        MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			helper.setFrom(fromId);
			helper.setCc(registerDto.getEmail());
			helper.setSubject("Welcome to Pharma App");
			helper.setSentDate(new Date());
			helper.addAttachment("pharma.jpeg", new ClassPathResource("com/pharma/image/pharma.jpeg"));
			helper.setText(message);
			
			sender.send(mimeMessage);

	        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
	    }
}
