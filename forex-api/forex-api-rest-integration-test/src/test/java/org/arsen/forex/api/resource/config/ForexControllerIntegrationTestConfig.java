package org.arsen.forex.api.resource.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(
        basePackages = "org.arsen.forex.persistence"
)
@EnableJpaRepositories(
        basePackages = "org.arsen.forex.persistence"
)
@ComponentScan(
        basePackages = {
                "org.arsen.forex.service",
                "org.arsen.forex.api.resource",
                "org.arsen.forex.api.facade",
        }
)
@Configuration
@EnableAutoConfiguration
public class ForexControllerIntegrationTestConfig {
}
