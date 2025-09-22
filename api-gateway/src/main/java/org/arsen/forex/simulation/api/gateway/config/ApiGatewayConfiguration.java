package org.arsen.forex.simulation.api.gateway.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients({"org.arsen.forex.simulation.api.client"})
class ApiGatewayConfiguration {
}