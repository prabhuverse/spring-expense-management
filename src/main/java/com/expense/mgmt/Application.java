package com.expense.mgmt;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
//@EnableScheduling
@EnableRetry
//@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true) // forces to use CGLIB
@SpringBootApplication(scanBasePackages = {"com.expense.mgmt"})
public class Application {

    public static void main(String[] args) {
        log.info("Application starting.....");
        SpringApplication.run(Application.class, args);
    }

}
