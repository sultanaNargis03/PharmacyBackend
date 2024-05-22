package com.pharma.service;

import java.util.List;
import java.util.Optional;

import com.pharma.model.Cart;
import com.pharma.model.CheckOut;

public interface ICartService 
{
	public String addToCart(String name,Integer medicineQuantity);
	public String removeFromCart(Integer id);
	public List<Cart> showCarts();
	public CheckOut checkout();

}
