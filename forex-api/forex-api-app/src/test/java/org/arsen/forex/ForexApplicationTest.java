package org.arsen.forex;

import org.arsen.forex.api.facade.CustomerServiceFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ForexApplicationTest {

    @Autowired
    private CustomerServiceFacade customerServiceFacade;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(customerServiceFacade);
    }
}
