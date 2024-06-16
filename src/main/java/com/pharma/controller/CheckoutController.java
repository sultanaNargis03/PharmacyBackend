package com.pharma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.model.CheckOut;
import com.pharma.service.CheckoutServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

@CrossOrigin(origins="http://localhost:5173/")
@RestController
@RequestMapping("/api-order")
@Tag(name="Pharmacy-Order",description="This API URL will help to order medicine")
public class CheckoutController 
{
	@Autowired
	CheckoutServiceImpl service;
	
	@Operation(summary="GET operation",description="API to place your order")
	@GetMapping("/checkout")
	public ResponseEntity<CheckOut> checkout() throws MessagingException
	{
		return new ResponseEntity<>(service.checkout(),HttpStatus.OK);
	}
	
	@Operation(summary="GET operation",description="API will show the list of order placed by you")
	@GetMapping("/list")
	public ResponseEntity<List<CheckOut>> fetchOrders()
	{
		return new ResponseEntity<>(service.fetchOrders(),HttpStatus.OK);
	}
	

}
