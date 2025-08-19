package com.udpt.medication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicationApplication.class, args);
	}

}
