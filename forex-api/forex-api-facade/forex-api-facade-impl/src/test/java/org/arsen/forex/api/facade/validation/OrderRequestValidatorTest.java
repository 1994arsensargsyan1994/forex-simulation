package org.arsen.forex.api.facade.validation;

import org.arsen.forex.api.facade.AbstractForexServiceFacadeTest;
import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.common.FailureDto;
import org.arsen.forex.service.order.OrderResultFailure;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static org.arsen.forex.api.facade.ForexServiceFacadeTestHelper.orderCreationRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderRequestValidatorTest extends AbstractForexServiceFacadeTest {

    @InjectMocks
    private OrderRequestValidator validator;

    @Test
    void testValidateNullRequestThrowsIllegalArgumentException() {
        Assertions.assertThatThrownBy(() -> validator.validate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Request must not be null");
    }

    @Test
    void testValidateInvalidAmountZeroFailsWithInvalidAmount() {
        OrderRequest req = orderCreationRequest(BigDecimal.ZERO);

        ValidationResult result = validator.validate(req);

        assertThat(result).isNotNull();
        assertThat(result.hasFailures()).isTrue();
        assertThat(result.failures())
                .anySatisfy(failureDto -> {
                    assertThat(failureDto.code()).isEqualTo(OrderResultFailure.INVALID_AMOUNT.code());
                    assertThat(failureDto.reason()).isEqualTo(OrderResultFailure.INVALID_AMOUNT.reason());
                });
    }

    @Test
    void validateInvalidAmountNegativeFailsWithInvalidAmount() {
        OrderRequest req = orderCreationRequest(new BigDecimal("-0.01"));

        ValidationResult result = validator.validate(req);

        assertThat(result).isNotNull();
        assertThat(result.hasFailures()).isTrue();
        assertThat(result.failures())
                .anySatisfy(failureDto -> {
                    assertThat(failureDto.code()).isEqualTo(OrderResultFailure.INVALID_AMOUNT.code());
                    assertThat(failureDto.reason()).isEqualTo(OrderResultFailure.INVALID_AMOUNT.reason());
                });
    }

    @Test
    void validateSameAccountFailsWithSameAccount() {
        String accountId = randomUUID();
        OrderRequest req = orderCreationRequest(accountId, accountId, new BigDecimal("10"));

        ValidationResult result = validator.validate(req);

        assertThat(result).isNotNull();
        assertThat(result.hasFailures()).isTrue();
        assertThat(result.failures())
                .anySatisfy(failureDto -> {
                    assertThat(failureDto.code()).isEqualTo(OrderResultFailure.SAME_ACCOUNT.code());
                    assertThat(failureDto.reason()).isEqualTo(OrderResultFailure.SAME_ACCOUNT.reason());
                });
    }

    @Test
    void testValidateBothInvalidAmountAndSameAccountReturnsBothFailures() {
        String accountId = randomUUID();
        OrderRequest req = orderCreationRequest(accountId, accountId, BigDecimal.ZERO);

        ValidationResult result = validator.validate(req);

        assertThat(result).isNotNull();
        assertThat(result.hasFailures()).isTrue();
        assertThat(result.failures())
                .extracting(FailureDto::code)
                .containsExactlyInAnyOrder(
                        OrderResultFailure.INVALID_AMOUNT.code(),
                        OrderResultFailure.SAME_ACCOUNT.code()
                );
    }

    @Test
    void testValidateValidRequestHasNoFailures() {
        OrderRequest req = orderCreationRequest(new BigDecimal("25.00"));

        ValidationResult result = validator.validate(req);

        assertThat(result).isNotNull();
        assertThat(result.hasFailures()).isFalse();
        assertThat(result.failures()).isEmpty();
    }

    @Override
    protected void verifyNoMoreMockInteractions() {

    }
}
