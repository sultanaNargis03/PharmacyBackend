package com.pharma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
public class PharmacyManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyManagementAppApplication.class, args);
	}

}
