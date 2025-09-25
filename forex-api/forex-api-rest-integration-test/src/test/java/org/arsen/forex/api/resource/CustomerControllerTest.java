package org.arsen.forex.api.resource;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.arsen.forex.api.resource.ControllerIntegrationTestHelper.*;

public class CustomerControllerTest extends AbstractForexControllerIntegrationTest {

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/customer")
                        .content(objectMapper.writeValueAsString(customerCreationRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.customerId").isNotEmpty())
                .andExpect(jsonPath("$.successful").value(Boolean.TRUE));
    }

    @Test
    public void testLookupDetails() throws Exception {
        mockMvc.perform(get("/customer/{id}/details", CUSTOMER_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.successful").value(Boolean.TRUE))
                .andExpect(jsonPath("$.details").exists());
    }
}
