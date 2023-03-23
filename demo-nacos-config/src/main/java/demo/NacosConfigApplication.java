package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacosConfigApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(NacosConfigApplication.class, args);
	}

}

