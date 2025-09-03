package com.example.demoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(scanBasePackages = "com.example.demoes")
@EnableElasticsearchRepositories(basePackages = "com.example.demoes")
public class DemoEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoEsApplication.class, args);
	}

}
