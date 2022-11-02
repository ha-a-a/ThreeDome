package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication(scanBasePackages = {"priv.tmy.study.*","com.example.*"})
@SpringBootApplication(scanBasePackages = {"com.example.*"})

public class DemoSpringApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DemoSpringApplication.class, args);
	}

}

