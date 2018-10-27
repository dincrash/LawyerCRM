package com.dincrash.LawyerCRM;

import com.dincrash.controller.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.dincrash"})
@EnableAutoConfiguration
public class LawyerCrmApplication {



    public static void main(String[] args) {
        SpringApplication.run(LawyerCrmApplication.class, args);






    }



}
