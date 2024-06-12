package com.pharma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharma.model.Medicine;
import com.pharma.repo.IMedicineRepo;

@Service

public class MedicineServiceImpl implements IMedicineService {

	@Autowired
	IMedicineRepo repo;
	
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
	// @PreAuthorize("hasAuthority('ADMIN')")
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
	 //@PreAuthorize("hasAuthority('ADMIN')")
	public String updateMedicine(Integer id,Medicine medicine) 
	{

		Optional<Medicine> existingMedicine = repo.findById(id);
		if(existingMedicine.isPresent())
		{
			existingMedicine.get().setmedicineName(medicine.getmedicineName());
			existingMedicine.get().setMedicineComposition(medicine.getMedicineComposition());
			existingMedicine.get().setMedicinePrice(medicine.getMedicinePrice());
			existingMedicine.get().setMedicineQuantity(medicine.getMedicineQuantity());
			existingMedicine.get().setExpiryDate(medicine.getExpiryDate());
			
			repo.save(existingMedicine.get());
			
			return "Medicine with id "+id +" updated successfully!!";
		}
		else
			return "Medicine with id "+id +" not exist!!";
	}

	@Override
	public Medicine showMedicineByMedicineName(String medicineName) 
	{
		return repo.findByMedicineName(medicineName).get();
	}

}
