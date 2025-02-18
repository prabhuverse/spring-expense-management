package com.example.demo_mvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemoMvnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMvnApplication.class, args);
	}

}
