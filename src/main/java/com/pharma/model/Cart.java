package com.pharma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart 
{
	@Id
	private Integer id;
	private Integer totalItem;
	private String itemName;
	private Double itemPrice;
	private Integer itemQuantity;
	
	public Cart() 
	{
		// TODO Auto-generated constructor stub
	}

	public Cart(Integer id, Integer totalItem, String itemName, Double itemPrice, Integer itemQuantity) {
		super();
		this.id = id;
		this.totalItem = totalItem;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", totalItem=" + totalItem + ", itemName=" + itemName + ", itemPrice=" + itemPrice
				+ ", itemQuantity=" + itemQuantity + "]";
	}

}
