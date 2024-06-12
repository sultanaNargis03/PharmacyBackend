package com.pharma.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pharma.model.Medicine;

public interface IMedicineRepo extends JpaRepository<Medicine, Integer>
{
	//@Query(value="SELECT * FROM medicine m WHERE m.medicineName=medicineName",nativeQuery=true)
	public Optional<Medicine> findByMedicineName(String medicineName);
}
