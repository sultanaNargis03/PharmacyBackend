package com.pharma.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthResponseDTO 
{
	 private String accessToken;
	 private String tokenType = "Bearer ";
	 private String role;
	 
	public AuthResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponseDTO(String accessToken, String role) {
		super();
		this.accessToken = accessToken;
		this.role = role;
	}

	public AuthResponseDTO(String accessToken, String tokenType, String role) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.role = role;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AuthResponseDTO [accessToken=" + accessToken + ", tokenType=" + tokenType + ", role=" + role + "]";
	}
	 
	
	
	    
}
