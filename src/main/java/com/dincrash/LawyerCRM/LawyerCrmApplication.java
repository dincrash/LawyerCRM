package com.dincrash.LawyerCRM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com"})
@EnableAutoConfiguration
public class LawyerCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(LawyerCrmApplication.class, args);
	}
}
