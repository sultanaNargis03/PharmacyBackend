package com.pharma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharma.model.Cart;
import com.pharma.model.CheckOut;
import com.pharma.model.UserEntity;
import com.pharma.repo.ICartRepository;
import com.pharma.repo.ICheckoutRepository;
import com.pharma.repo.UserRepository;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

	
	@Autowired
	ICartRepository cartRepo;
	
	@Autowired
	ICheckoutRepository orderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public Optional<UserEntity> getCurrentUser()
	{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByUsername(authentication.getName());
    }
	
	@Override
	public CheckOut checkout() 
	{
		UserEntity user = getCurrentUser().get();
		
		
		List<Cart> cartItems = cartRepo.findByUser(user).get();
		if(cartItems.isEmpty())
		{
			
			throw new RuntimeException("you can't perform checkout when your cart is empty!!");
						
		}
		CheckOut order=new CheckOut();
		
		List<String> iname=new ArrayList<>();
		Double iprice=0.0;
		Integer iquantity=0;
		
		for(Cart carts:cartItems)
		{
			iname.add(carts.getItemName());
			iprice+=carts.getItemPrice();
			iquantity+=carts.getItemQuantity();
			
		}
		
		order.setItemNames(iname);
		order.setTotalPrice(iprice);
		order.setTotalItem(iquantity);
		order.setUser(user);
		orderRepo.save(order);
		
		for(Cart carts:cartItems)
		{
			cartRepo.deleteById(carts.getId());
			
		}
		
		return order;
	}

	@Override
	public List<CheckOut> fetchOrders() 
	{
		UserEntity user = getCurrentUser().orElseThrow(()->new RuntimeException("current user not found!!"));
		List<CheckOut> orders = orderRepo.findByUser(user).orElseThrow(()->new RuntimeException("you haven't placed any order yet!!"));
		System.out.println(orders);
		return orders;
		
	}	

}
