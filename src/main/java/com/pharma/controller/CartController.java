package com.pharma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.service.CartServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api-cart")
public class CartController {
	@Autowired
	CartServiceImpl service;
	@Operation(summary="POST operation",description="API will accept medicine name and json medicine quantity and will add the medicine to the cart")
	@PostMapping("/cart/{medicineName}")
	public ResponseEntity<String> addToCart(@PathVariable("medicineName") String medicineName, @RequestBody Integer medicineQuantity)
	{
		String msg = service.addToCart(medicineName,medicineQuantity);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}
