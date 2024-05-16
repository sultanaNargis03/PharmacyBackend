package com.pharma.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharma.model.Cart;
import com.pharma.model.Medicine;
import com.pharma.model.UserEntity;
import com.pharma.repo.ICartRepository;
import com.pharma.repo.IMedicineRepo;
import com.pharma.repo.UserRepository;

@Service
public class CartServiceImpl implements ICartService
{

	@Autowired
	IMedicineRepo repo;
	
	@Autowired
	ICartRepository cartRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public Optional<UserEntity> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByUsername(authentication.getName());
    }
	
	@Override
	public String addToCart(String medicineName,Integer medicineQuantity) 
	{
		//List<Cart> carts = cartRepo.findAll();
	
		UserEntity user = getCurrentUser().get();
		Long uid=user.getId();
		
//		boolean flag=false;
//		Optional<UserEntity> ex = userRepo.findById(uid);
//		List<Cart> userCart = ex.get().getCart();
//		for(Cart c:userCart)
//		{
//			System.out.println("ItemName: "+c.getItemName());
//			if(c.getItemName().equals(medicineName))
//			{
//				flag=true;
//			}
//		}
		
		Medicine med=repo.findBymedicineName(medicineName);
		Cart cart=new Cart();
		
		if(medicineQuantity>med.getMedicineQuantity())
		{
			return "Medicine "+med.getmedicineName()+" not availble!!";
		}
		else
		{
			
			if(cartRepo.existsByItemName(medicineName))
			{
					
//				if(flag==true)
//				{
					Cart existingItem=cartRepo.findByItemName(medicineName);
					medicineQuantity+=existingItem.getItemQuantity();
					cart.setId(existingItem.getId());
		//		}
						
			}		
					
				
				
			}
			
			Double price=med.getMedicinePrice();
			Double total=price*medicineQuantity;
			med.setMedicineQuantity(med.getMedicineQuantity()-medicineQuantity);
			repo.save(med);
	
			cart.setItemName(medicineName);
			cart.setItemQuantity(medicineQuantity);
			cart.setItemPrice(total);
			cart.setUser(user);
			//System.out.println(cart.getUser().getId());
			cartRepo.save(cart);
			
			return "Medicine "+med.getmedicineName()+" added to cart successfully!!"+" $ "+total;
		
	}

	@Override
	public String removeFromCart(Integer id) {
		
		Optional<Cart> cart=cartRepo.findById(id);
		if(cart.isPresent())
		{
			cartRepo.deleteById(id);
			return "deleted!!";
		}
		return "id "+id+ "not exist!";
	}	

}
