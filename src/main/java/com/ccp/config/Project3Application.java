package com.ccp.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ccp.service.FileProcessingService")
public class Project3Application {
    public static void main(String[] args) {
        SpringApplication.run(Project3Application.class, args);
    }
}
