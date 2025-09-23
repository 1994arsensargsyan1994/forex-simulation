package org.arsen.forex.service.order;

import java.math.BigDecimal;

public record OrderParameters(String sourceAccountId,
                              String targetAccountId, BigDecimal amount,
                              String idempotencyKey) {

}