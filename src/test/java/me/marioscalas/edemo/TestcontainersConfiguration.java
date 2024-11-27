package me.marioscalas.edemo;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.hibernate.dialect.PostgreSQLDialect;

import lombok.RequiredArgsConstructor;
@TestConfiguration(proxyBeanMethods = false)
@ContextConfiguration(initializers = {TestcontainersConfiguration.Initializer.class})
class TestcontainersConfiguration {
    @ServiceConnection
    @RestartScope
    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:13.3"))
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        postgresContainer.start();
        return postgresContainer;
    }

    @RequiredArgsConstructor
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        
        private final PostgreSQLContainer<?> postgreSQLContainer;
        
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
              "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
              "spring.datasource.username=" + postgreSQLContainer.getUsername(),
              "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
