package com.mf.myfinances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
@EnableConfigurationProperties()
public class MyfinancesApplication {

	private static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		MyfinancesApplication.applicationContext = SpringApplication.run(MyfinancesApplication.class, args);
	}

}
