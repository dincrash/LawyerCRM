package com.dincrash.LawyerCRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.dincrash"})
@EnableAutoConfiguration
public class LawyerCrmApplication {
    private final static Logger logger = LogManager.getLogger(LawyerCrmApplication.class);



    public static void main(String[] args) {
        SpringApplication.run(LawyerCrmApplication.class, args);





    }



}
