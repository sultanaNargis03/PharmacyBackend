package com.pharma.service;

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
	
	public Optional<UserEntity> getCurrentUser()
	{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByUsername(authentication.getName());
    }
	
	@Override
	public String addToCart(String medicineName,Integer medicineQuantity) 
	{
			
		UserEntity user = getCurrentUser().get();
		
		Cart existingCart=cartRepo.findByUserAndItemName(user, medicineName);
		System.out.println("existingCart: "+existingCart);
		Medicine med=repo.findBymedicineName(medicineName);
		
		Cart cart=new Cart();
		
		if(medicineQuantity>med.getMedicineQuantity())
		{
			return "Medicine "+med.getmedicineName()+" not availble!!";
		}
		else
		{
			
			
			if(existingCart!=null)
			{
				medicineQuantity+=existingCart.getItemQuantity();
				cart.setId(existingCart.getId());
			
			}		
			
			Double price=med.getMedicinePrice();
			Double total=price*medicineQuantity;
			med.setMedicineQuantity(med.getMedicineQuantity()-medicineQuantity);
			repo.save(med);
	
			cart.setItemName(medicineName);
			cart.setItemQuantity(medicineQuantity);
			cart.setItemPrice(total);
			cart.setUser(user);
			cartRepo.save(cart);
					
			return "Medicine "+med.getmedicineName()+" added to cart successfully!!"+" $ "+total;
		}
	}

	@Override
	public String removeFromCart(Integer id) 
	{
		Long currentuserId = getCurrentUser().get().getId();
		
		Optional<Cart> cart=cartRepo.findById(id);
		
		
			if(cart.isPresent())
			{
				if(cart.get().getUser().getId()==currentuserId)
				{
					cartRepo.deleteById(id);
					return cart.get().getItemName()+" with id "+id+" has been deleted!!";
				}
				return "you are not authorized to remove the item with id "+id;
			}
			
				return "id "+id+ " not exist!";
			
	}


	@Override
	public List<Cart> showCarts()
	{
	//	return cartRepo.findAll();
		 UserEntity user = getCurrentUser().get();
		 
		List<Cart> cartItems = cartRepo.findByUser(user);
		if(cartItems.isEmpty())
		{
			//return null;
			throw new RuntimeException("your cart is empty!!");
		}
		 return cartItems;
	}	

}
