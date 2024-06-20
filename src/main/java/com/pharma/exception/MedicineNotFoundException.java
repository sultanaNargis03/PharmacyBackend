package com.pharma.exception;

public class MedicineNotFoundException extends RuntimeException{

	public MedicineNotFoundException(String msg) {
		
		super(msg);
		System.out.println(msg);
	}

}
