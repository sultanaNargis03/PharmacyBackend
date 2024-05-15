package com.pharma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.model.Cart;
import com.pharma.model.Medicine;
import com.pharma.model.MedicineWrapper;
import com.pharma.repo.ICartRepository;
import com.pharma.repo.IMedicineRepo;

@Service
public class MedicineServiceImpl implements IMedicineService {

	@Autowired
	IMedicineRepo repo;
	
	@Autowired
	ICartRepository cartRepo;
	
	@Override
	public String addMedicine(Medicine medicine) 
	{
		List<Medicine> med = repo.findAll();
		for(Medicine m:med)
		{
			if(medicine.getmedicineName().equalsIgnoreCase(m.getmedicineName())&&medicine.getMedicineComposition().equalsIgnoreCase(m.getMedicineComposition()))
			{
				return "Medicine "+medicine.getmedicineName() +" with composition "+medicine.getMedicineComposition() + " already exist!! you can't add it again!!you can update the quantity";
			}
		}
		repo.save(medicine);
		return medicine.getmedicineName()+" added successfully!!";
	}

	@Override
	public List<Medicine> showAllMedicines() 
	{
		
		return repo.findAll();
	}

	@Override
	public Medicine showMedicineById(Integer id) 
	{
		
		return repo.findById(id).get();
		
	}

	@Override
	public String deleteMedicineById(Integer id) 
	{
		Optional<Medicine> medicineId = repo.findById(id);
		if(medicineId.isPresent())
		{
			repo.deleteById(id);
			return "Medicine with id "+id +" deleted successfully!!";
		}
		
		return "Medicine with id "+id +" not exist!!";
	}

	@Override
	public String updateMedicine(Integer id,Medicine medicine) 
	{

		Optional<Medicine> optional = repo.findById(id);
		if(optional.isPresent())
		{
			repo.save(medicine);
			return "Medicine with id "+id +" updated successfully!!";
		}
		else
			return "Medicine with id "+id +" not exist!!";
	}


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
