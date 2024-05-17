package com.pharma.service;

public interface ICartService 
{
	public String addToCart(String name,Integer medicineQuantity);
	public String removeFromCart(Integer id);

}
