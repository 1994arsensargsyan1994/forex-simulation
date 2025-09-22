package org.arsen.forex.simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ForexSimulationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForexSimulationApplication.class);
    }
}
