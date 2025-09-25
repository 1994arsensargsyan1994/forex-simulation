package org.arsen.forex.api.resource.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.api.model.response.LookupOrderDetailsResponse;
import org.arsen.forex.api.model.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrderController {

    @PostMapping
    ResponseEntity<OrderResponse> order(@RequestHeader("Idempotency-Key") String idempotencyKey,
                                        @RequestBody OrderRequest request
    );

    @GetMapping(path = "/{id}/details")
    ResponseEntity<LookupOrderDetailsResponse> lookupDetails(@PathVariable(name = "id") @NotNull @Positive Long id);
}
