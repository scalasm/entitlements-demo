package me.marioscalas.edemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class EntitlementsDemoApplicationTests {

	@Autowired
	private PostgreSQLContainer<?> postgreSQLContainer;

	@Test
	void contextLoads() {
	}
}
