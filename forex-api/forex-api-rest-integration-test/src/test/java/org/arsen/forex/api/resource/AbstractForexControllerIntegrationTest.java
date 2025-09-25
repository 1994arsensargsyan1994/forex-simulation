package org.arsen.forex.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.arsen.forex.api.resource.config.ForexControllerIntegrationTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
        classes = ForexControllerIntegrationTestConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@Testcontainers
public abstract class AbstractForexControllerIntegrationTest {


    @Container
    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:13.1");

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @DynamicPropertySource
    static void registerDynamicProperties(final DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        propertyRegistry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);

        propertyRegistry.add("spring.jpa.show-sql", () -> Boolean.TRUE);
        propertyRegistry.add("spring.batch.job.enabled", () -> Boolean.FALSE);

        propertyRegistry.add("spring.flyway.enabled", () -> Boolean.TRUE);
        propertyRegistry.add("spring.flyway.locations", () -> "classpath:");
    }
}
