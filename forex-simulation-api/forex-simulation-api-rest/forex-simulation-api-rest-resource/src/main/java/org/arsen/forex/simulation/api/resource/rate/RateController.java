package org.arsen.forex.simulation.api.resource.rate;

import org.arsen.forex.simulation.api.model.response.LookupRatesResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rate")
public interface RateController {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LookupRatesResponse> lookup();
}