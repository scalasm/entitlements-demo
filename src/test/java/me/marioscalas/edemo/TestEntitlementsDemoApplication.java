package me.marioscalas.edemo;

import org.springframework.boot.SpringApplication;

public class TestEntitlementsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(EntitlementsDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
