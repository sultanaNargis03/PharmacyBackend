package com.pharma.service;

import java.util.List;
import com.pharma.model.Cart;

public interface ICartService 
{
	public String addToCart(String name,Integer medicineQuantity);
	public String removeFromCart(Integer id);
	public List<Cart> showCarts();
	

}
