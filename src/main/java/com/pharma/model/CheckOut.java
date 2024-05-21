package com.pharma.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class CheckOut
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private List<String> itemNames;
	private Double totalPrice;
	private Integer totalItem;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<UserEntity> user;
	
	public CheckOut() 
	{
		
	}

	public CheckOut(Integer id, List<String> itemNames, Double totalPrice, Integer totalItem, List<UserEntity> user) {
		super();
		this.id = id;
		this.itemNames = itemNames;
		this.totalPrice = totalPrice;
		this.totalItem = totalItem;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getItemNames() {
		return itemNames;
	}

	public void setItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public List<UserEntity> getUser() {
		return user;
	}

	public void setUser(List<UserEntity> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CheckOut [id=" + id + ", itemNames=" + itemNames + ", totalPrice=" + totalPrice + ", totalItem="
				+ totalItem + ", user=" + user + "]";
	}	

}
