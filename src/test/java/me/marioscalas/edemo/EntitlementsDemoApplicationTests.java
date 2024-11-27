package me.marioscalas.edemo;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import lombok.RequiredArgsConstructor;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class EntitlementsDemoApplicationTests {

	@Autowired
	private PostgreSQLContainer<?> postgreSQLContainer;

	@Test
	void contextLoads() {
	}
}
