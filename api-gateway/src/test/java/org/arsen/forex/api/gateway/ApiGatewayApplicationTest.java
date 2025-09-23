package org.arsen.forex.api.gateway;

import org.arsen.forex.api.client.ForexClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiGatewayApplicationTest {

    @Autowired
    private ForexClient forexClient;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(forexClient);
    }
}