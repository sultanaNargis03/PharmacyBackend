package com.pharma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.model.Medicine;
import com.pharma.service.MedicineServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins="http://localhost:5173/")
@RestController
@RequestMapping("/api")
@Tag(name="Pharmacy-medicine",description="This API URL will help to manage medicine")
public class MedicineController 
{
	@Autowired
	MedicineServiceImpl service;
	
	@Operation(summary="POST operation",description="API will accept json medicine(don't need to give id,id is auto incremented) obj and add new medicine")
	@PostMapping("/medicine")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine)
	{
		String msg=service.addMedicine(medicine);
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@Operation(summary="GET operation",description="API will get all available medicine info")
	@GetMapping("/medicine")
	public ResponseEntity<List<Medicine>> showMedicine()
	{
		List<Medicine> medicine=service.showAllMedicines();
		return new ResponseEntity<>(medicine,HttpStatus.OK);
	}
	
	@Operation(summary="GET operation",description="API will accept medicine id and get medicine info")
	@GetMapping("/medicine/{id}")
	public ResponseEntity<Medicine> showMedicineById(@PathVariable("id") Integer id)
	{
		Medicine medicine = service.showMedicineById(id);
		return new ResponseEntity<>(medicine,HttpStatus.OK);
	}
	
	@Operation(summary="GET operation",description="API will accept medicine name and get medicine info")
	@GetMapping("/getmedicine/{medicineName}")
	public ResponseEntity<Medicine> showMedicineByMedicineName(@PathVariable("medicineName") String medicineName)
	{
		Medicine medicine = service.showMedicineByMedicineName(medicineName);
		return new ResponseEntity<>(medicine,HttpStatus.OK);
	}
	
	@Operation(summary="PUT operation",description="API will accept json medicine obj and update medicine info")
	@PutMapping("/update-medicine/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateMedicine(@PathVariable("id") Integer id,@RequestBody Medicine medicine)
	{
		String msg = service.updateMedicine(id,medicine);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@Operation(summary="DELETE operation",description="API will accept medicine id and get delete medicine")
	@DeleteMapping("/medicine/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteMedicineById(@PathVariable("id") Integer id)
	{
		String msg = service.deleteMedicineById(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	
}
