package com.pharma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.model.Cart;
import com.pharma.model.Medicine;
import com.pharma.repo.ICartRepository;
import com.pharma.repo.IMedicineRepo;

@Service
public class CartServiceImpl implements ICartService
{

	@Autowired
	IMedicineRepo repo;
	
	@Autowired
	ICartRepository cartRepo;
	
	@Override
	public String addToCart(String medicineName,Integer medicineQuantity) 
	{
		
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
				Cart existingItem=cartRepo.findByItemName(medicineName);
				medicineQuantity+=existingItem.getItemQuantity();
				cart.setId(existingItem.getId());
			}
			
			Double price=med.getMedicinePrice();
			Double total=price*medicineQuantity;
			med.setMedicineQuantity(med.getMedicineQuantity()-medicineQuantity);
			repo.save(med);
	
			cart.setItemName(medicineName);
			cart.setItemQuantity(medicineQuantity);
			cart.setItemPrice(total);
			cartRepo.save(cart);
			
			return "Medicine "+med.getmedicineName()+" added to cart successfully!!"+" $ "+total;
		}
	}	

}
