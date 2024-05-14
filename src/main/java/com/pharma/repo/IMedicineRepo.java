package com.pharma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharma.model.Medicine;

public interface IMedicineRepo extends JpaRepository<Medicine, Integer>
{
	//@Query(value="SELECT * FROM medicine m WHERE m.medicineName=medicineName",nativeQuery=true)
	public Medicine findBymedicineName(String medicineName);
}
