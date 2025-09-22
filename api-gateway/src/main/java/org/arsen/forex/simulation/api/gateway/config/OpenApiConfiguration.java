package org.arsen.forex.simulation.api.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

@Configuration
@PropertySource(value = "classpath:api-documentation.properties")
@OpenAPIDefinition
class OpenApiConfiguration {

    @Bean
    OpenAPI customOpenAPI(
            @Value("${urm.openapi.info.title}") final String title,
            @Value("${urm.openapi.info.version}") final String version,
            @Value("${urm.openapi.info.description}") final String description
    ) {
        return new OpenAPI()
                .components(
                        new Components()
                )
                .info(
                        new Info()
                                .title(title)
                                .version(version)
                                .description(description)
                );
    }
}