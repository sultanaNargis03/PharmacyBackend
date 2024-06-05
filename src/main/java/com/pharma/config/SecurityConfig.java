package com.pharma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity	
public class SecurityConfig
{
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	public JwtAuthEntryPoint authEntryPoint;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http
		.csrf(csrf-> csrf.disable())
		.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("/api/auth/**").permitAll();
            authorize.requestMatchers("/v3/**", "/swagger-ui/**").permitAll();
            authorize.anyRequest().authenticated();
        })
		.exceptionHandling(ex ->ex.authenticationEntryPoint(authEntryPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();
		
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
	        return new JWTAuthenticationFilter();
	    }
    
  
}
