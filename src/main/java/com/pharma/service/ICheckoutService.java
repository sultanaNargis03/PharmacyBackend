package com.pharma.service;

import java.util.List;

import com.pharma.model.CheckOut;

import jakarta.mail.MessagingException;

public interface ICheckoutService
{
	public CheckOut checkout() throws MessagingException;
	public List<CheckOut> fetchOrders();
}
