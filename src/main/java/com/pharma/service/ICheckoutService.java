package com.pharma.service;

import java.util.List;

import com.pharma.model.CheckOut;

public interface ICheckoutService
{
	public CheckOut checkout();
	public List<CheckOut> fetchOrders();
}
