package com.pharma.exception;

public class MedicineNotFoundException extends RuntimeException{

	public MedicineNotFoundException(String msg) {
		System.out.println(msg);
	}

}
