package com.pharma.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Entity
public class UserEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;
	private String phnNo;
	
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Cart> cart;

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<CheckOut> checkout;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Roles> roles=new ArrayList<>();
	

	public UserEntity() 
	{
		
	}	

	public UserEntity(Long id, String username, String password, String email, String phnNo, List<Cart> cart,
			List<CheckOut> checkout, List<Roles> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phnNo = phnNo;
		this.cart = cart;
		this.checkout = checkout;
		this.roles = roles;
	}




	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<CheckOut> getCheckout() {
		return checkout;
	}

	public void setCheckout(List<CheckOut> checkout) {
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phnNo=" + phnNo + ", cart=" + cart + ", checkout=" + checkout + ", roles=" + roles + "]";
	}
	

}
