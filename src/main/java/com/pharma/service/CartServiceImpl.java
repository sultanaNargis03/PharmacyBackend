package com.pharma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharma.exception.MedicineNotAvailableException;
import com.pharma.exception.MedicineNotFoundException;
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
		UserEntity user=getCurrentUser().orElseThrow(()->new RuntimeException("current user not found!!"));	
		System.out.println("CurrentUser: "+user);
		Cart existingCart=cartRepo.findByUserAndItemName(user, medicineName);
		System.out.println("existingCart: "+existingCart);
		 
		Medicine med = repo.findByMedicineName(medicineName).orElseThrow(()->new MedicineNotFoundException("Medicine not found!!"));
		
		Cart cart=new Cart();
		
		if(medicineQuantity>med.getMedicineQuantity())
		{		
			
			throw new MedicineNotAvailableException("Medicine " + med.getmedicineName() + " quantity: " + medicineQuantity + " not available. Available quantity: " + med.getMedicineQuantity());
			//return "Medicine "+med.getmedicineName()+" quantity: "+medicineQuantity +" not availble "+"\n Available quantity:"+med.getMedicineQuantity();
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
		String cartItem = cart.get().getItemName();
		Integer cartQuantity = cart.get().getItemQuantity();
		
			if(cart.isPresent())
			{
				if(cart.get().getUser().getId()==currentuserId)
				{
					Medicine med = repo.findByMedicineName(cartItem).get();
					 med.setMedicineQuantity(med.getMedicineQuantity()+cartQuantity);
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
	
		 UserEntity user = getCurrentUser().orElseThrow(()->new RuntimeException("current user not found!!"));
		 System.out.println("User :"+user);
		 List<Cart> cartItems = cartRepo.findByUser(user).orElseThrow(()->new RuntimeException("your cart is empty!!"));
		 return cartItems;
	}

	

}
