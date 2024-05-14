package com.pharma.service;

import java.util.List;

import com.pharma.model.Medicine;

public interface IMedicineService 
{

	public String addMedicine(Medicine medicine);
	public List<Medicine> showAllMedicines();
	public Medicine showMedicineById(Integer id);
	public String deleteMedicineById(Integer id);
	public String updateMedicine(Integer id,Medicine medicine);
	public String addToCart(String name,Integer medicineQuantity);

}
