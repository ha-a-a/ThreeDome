package demo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@NacosPropertySource(dataId = "example",autoRefreshed = true)
public class NacosConfigApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(NacosConfigApplication.class, args);
	}

}

