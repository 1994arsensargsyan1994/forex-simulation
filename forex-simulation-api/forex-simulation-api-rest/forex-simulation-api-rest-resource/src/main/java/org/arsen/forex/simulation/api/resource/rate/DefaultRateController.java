package org.arsen.forex.simulation.api.resource.rate;

import org.arsen.forex.simulation.api.facade.RateServiceFacade;
import org.arsen.forex.simulation.api.model.response.LookupRatesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultRateController implements RateController {

    private final RateServiceFacade rateServiceFacade;

    public DefaultRateController(RateServiceFacade rateServiceFacade) {
        this.rateServiceFacade = rateServiceFacade;
    }

    @Override
    public ResponseEntity<LookupRatesResponse> lookup() {
        return ResponseEntity.ok(rateServiceFacade.lookup());
    }

}
