package com.pharma.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.exception.MedicineNotAvailableException;
import com.pharma.exception.MedicineNotFoundException;
import com.pharma.model.Cart;
import com.pharma.service.CartServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins="http://localhost:5173/")
@RestController
@RequestMapping("/api-cart")
@Tag(name="Pharmacy-cart",description="This API URL will help to add medicines to cart")
public class CartController 
{
	@Autowired
	CartServiceImpl service;
	
	
	@Operation(summary="POST operation",description="API will accept medicine name and json medicine quantity and will add the medicine to the cart")
	@PostMapping("/cart/{medicineName}")
	public ResponseEntity<String> addToCart(@PathVariable("medicineName") String medicineName, @RequestBody Integer medicineQuantity)
	{
		try {
		String msg = service.addToCart(medicineName,medicineQuantity);
		return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch (MedicineNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (MedicineNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
	}
	@Operation(summary="GET operation",description="API will show user carts")
	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> showCarts()
	{
		return new ResponseEntity<>(service.showCarts(),HttpStatus.OK);
	}
	@Operation(summary="DELETE operation",description="API will accept cart id delete the cart")
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<String> removeFromCart(@PathVariable("id")Integer id)
	{
		String msg=service.removeFromCart(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}