package com.biruk.ERS.p1ERS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.biruk.ERS.models")
@ComponentScan("com.biruk.ERS")
public class ErsApplication {
	public static void main(String[] args) {

		SpringApplication.run(ErsApplication.class, args);

		System.out.println("Employee Reimbursement System App is running...");
	}

}
