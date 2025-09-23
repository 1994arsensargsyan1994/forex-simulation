package org.arsen.forex.api.resource.order;

import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.api.model.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
public interface OrderController {

    @PostMapping
    ResponseEntity<OrderResponse> order(@RequestHeader("Idempotency-Key") String idempotencyKey,
                                        @RequestBody OrderRequest request
    );
}
