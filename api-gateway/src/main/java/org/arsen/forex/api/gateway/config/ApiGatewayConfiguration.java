package org.arsen.forex.api.gateway.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients({"org.arsen.forex.api.client"})
class ApiGatewayConfiguration {
}