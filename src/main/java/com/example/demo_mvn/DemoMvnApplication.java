package com.example.demo_mvn;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class DemoMvnApplication {

    public static void main(String[] args) {
        log.info("Application starting.....");
        SpringApplication.run(DemoMvnApplication.class, args);
    }

}
