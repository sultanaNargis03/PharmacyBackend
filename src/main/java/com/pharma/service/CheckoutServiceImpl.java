package com.pharma.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharma.model.Cart;
import com.pharma.model.CheckOut;
import com.pharma.model.UserEntity;
import com.pharma.repo.ICartRepository;
import com.pharma.repo.ICheckoutRepository;
import com.pharma.repo.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

	
	@Autowired
	ICartRepository cartRepo;
	
	@Autowired
	ICheckoutRepository orderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("{spring.mail.username}")
	private String fromId;
	
	public Optional<UserEntity> getCurrentUser()
	{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByUsername(authentication.getName());
    }
	
	@Override
	public CheckOut checkout() throws MessagingException 
	{
		List<String> iname=new ArrayList<>();
		Double iprice=0.0;
		Integer iquantity=0;
		int i=0;
		UserEntity user = getCurrentUser().get();
		
		
		List<Cart> cartItems = cartRepo.findByUser(user).get();
		if(cartItems.isEmpty())
		{
			
			throw new RuntimeException("you can't perform checkout when your cart is empty!!");
						
		}
		CheckOut order=new CheckOut();
		
		
		System.out.println("rendering:"+ ++i);
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
		
		
		
		String message="Assalamu alaikum "+user.getUsername()+" Your order has been successfully placed\n"
				+ "\nOrder details:\n"
				+ "Order Id: "+order.getId()
				+"\nTotal Order Items: "+order.getTotalItem()
				+"\nOrder Items: "+order.getItemNames()
				+"\nTotal Price: "+order.getTotalPrice();
		
        MimeMessage mimeMessage = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		helper.setFrom(fromId);
		helper.setCc(user.getEmail());
		helper.setSubject("your order for "+order.getItemNames()+" placed successfully");
		helper.setSentDate(new Date());
		helper.addAttachment("pharma.jpeg", new ClassPathResource("com/pharma/image/pharma.jpeg"));
		helper.setText(message);
		
		sender.send(mimeMessage);
		
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
