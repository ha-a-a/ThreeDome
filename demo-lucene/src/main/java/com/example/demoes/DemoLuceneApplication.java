package com.example.demoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.demoes")
public class DemoLuceneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoLuceneApplication.class, args);
	}

}
