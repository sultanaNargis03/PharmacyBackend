package com.pharma.model;

public class RegisterDto 
{
	private String username;
	private String password;
	private String email;
	private String phnNo;
	
	public RegisterDto(String username, String password, String email, String phnNo) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phnNo = phnNo;
	}
	public RegisterDto() {
		super();
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhnNo() {
		return phnNo;
	}
	public void setPhnNo(String phnNo) {
		this.phnNo = phnNo;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "RegisterDto [username=" + username + ", password=" + password + ", email=" + email + ", phnNo=" + phnNo
				+ "]";
	}
	
}
