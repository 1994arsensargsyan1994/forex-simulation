package org.arsen.forex.simulation.api.gateway.forex;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.arsen.forex.simulation.api.client.ForexClient;
import org.arsen.forex.simulation.api.model.request.AccountCreationRequest;
import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.request.OrderRequest;
import org.arsen.forex.simulation.api.model.response.*;
import org.arsen.forex.simulation.common.FailureDto;
import org.arsen.forex.simulation.common.api.model.CommonFailures;
import org.arsen.forex.simulation.common.api.model.FailureAwareResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@Validated
@RestController
@RequestMapping()
public class ForexController {

    private static final Logger logger = LoggerFactory.getLogger(ForexController.class);

    private static final long DEFAULT_API_TIMEOUT = 5_000L;

    private final Executor executor;

    private final ForexClient forexClient;

    public ForexController(
            @Qualifier("apiGatewayExecutor") final Executor executor,
            final ForexClient forexClient
    ) {
        this.executor = executor;
        this.forexClient = forexClient;
    }

    @Operation(
            summary = "${customer.create.operation.summary}",
            description = "${customer.create.operation.description}",
            responses = @ApiResponse(
                    content = @Content(schema = @Schema(oneOf = CustomerCreationRequest.class))
            )
    )
    @PostMapping(
            path = "/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeferredResult<ResponseEntity<CustomerCreationResponse>> createCustomer(
            @Valid @RequestBody final CustomerCreationRequest request
    ) {
        logger.info("Creating Customer for given request: {}.", request);
        return deferredResponse(
                CompletableFuture.supplyAsync(() -> forexClient.createCustomer(request), executor),
                CustomerCreationResponse::new
        );
    }

    @Operation(
            summary = "${customer.lookup.details.operation.summary}",
            description = "${customer.lookup.details.operation.description}",
            responses = @ApiResponse(
                    content = @Content(schema = @Schema(oneOf = LookupCustomerDetailsResponse.class))
            )
    )
    @GetMapping(path = "/customer/{id}/details",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeferredResult<ResponseEntity<LookupCustomerDetailsResponse>> lookupDetails(
            @PathVariable("id") @NotNull @Positive Long id
    ) {
        logger.info("Lookup Customer details for given ID: {}.", id);
        return deferredResponse(
                CompletableFuture.supplyAsync(() -> forexClient.lookupCustomerDetails(id), executor),
                LookupCustomerDetailsResponse::new
        );
    }

    @Operation(
            summary = "${account.create.operation.summary}",
            description = "${account.create.operation.description}",
            responses = @ApiResponse(
                    content = @Content(schema = @Schema(oneOf = CustomerCreationRequest.class))
            )
    )
    @PostMapping(
            path = "/account/{customerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeferredResult<ResponseEntity<AccountCreationResponse>> createAccount(
            @NotNull @PathVariable(name = "customerId") Long customerId,
            @Valid @RequestBody final AccountCreationRequest request
    ) {
        logger.info("Creating Account for given request: {} and customer id: {}.", request, customerId);
        return deferredResponse(
                CompletableFuture.supplyAsync(() -> forexClient.createAccount(customerId, request), executor),
                AccountCreationResponse::new
        );
    }

    @Operation(
            summary = "${order.create.operation.summary}",
            description = "${order.create.operation.description}",
            responses = @ApiResponse(
                    content = @Content(schema = @Schema(oneOf = CustomerCreationRequest.class))
            )
    )
    @PostMapping(
            path = "/order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeferredResult<ResponseEntity<OrderResponse>> createAccount(
            @NotNull @RequestHeader("Idempotency-Key") String idempotencyKey,
            @Valid @RequestBody final OrderRequest request
    ) {
        logger.info("Creating Order for given request: {} and idempotency key: {}.", request, idempotencyKey);
        return deferredResponse(
                CompletableFuture.supplyAsync(() -> forexClient.createOrder(idempotencyKey, request), executor),
                OrderResponse::new
        );
    }

    @Operation(
            summary = "${rate.lookup.operation.summary}",
            description = "${rate.lookup.operation.description}",
            responses = @ApiResponse(
                    content = @Content(schema = @Schema(oneOf = LookupCustomerDetailsResponse.class))
            )
    )
    @GetMapping(path = "/rate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeferredResult<ResponseEntity<LookupRatesResponse>> lookupRates() {
        return deferredResponse(
                CompletableFuture.supplyAsync(forexClient::lookupRates, executor),
                LookupRatesResponse::new
        );
    }

    private <R extends FailureAwareResponse> DeferredResult<ResponseEntity<R>> deferredResponse(
            final CompletableFuture<R> responsePromise,
            final Supplier<R> constructor
    ) {
        final DeferredResult<ResponseEntity<R>> deferredResponse = new DeferredResult<>(DEFAULT_API_TIMEOUT);
        responsePromise.whenComplete((response, throwable) -> {
            if (throwable == null) {
                deferredResponse.setResult(responseFor(HttpStatus.OK, response));
            } else {
                logger.error("Request failed.", throwable);
                deferredResponse.setErrorResult(internalServerError(constructor));
            }
        });
        return deferredResponse;
    }

    private static <R extends FailureAwareResponse> ResponseEntity<R> internalServerError(final Supplier<R> constructor) {
        final R response = constructor.get();
        response.setFailures(
                List.of(
                        new FailureDto(
                                CommonFailures.INTERNAL_SERVER_ERROR.code(),
                                CommonFailures.INTERNAL_SERVER_ERROR.reason()
                        )
                )
        );
        return responseFor(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }

    private static <R extends FailureAwareResponse> ResponseEntity<R> responseFor(final HttpStatus httpStatus, final R body) {
        return ResponseEntity.status(httpStatus).body(body);
    }
}


