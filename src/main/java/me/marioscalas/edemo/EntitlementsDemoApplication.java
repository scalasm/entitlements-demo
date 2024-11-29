package me.marioscalas.edemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EntitlementsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntitlementsDemoApplication.class, args);
	}

}
