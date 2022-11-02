package demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author mengyuetang
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "demo.dubbo.service")
public class DubboProviderApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DubboProviderApplication.class, args);
	}

}

