package org.arsen.forex.simulation.persistence.order;

import jakarta.persistence.*;
import org.arsen.forex.simulation.common.OrderStatus;
import org.arsen.forex.simulation.persistence.AuditableEntity;
import org.arsen.forex.simulation.persistence.account.PersistentAccount;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Cacheable
@SequenceGenerator(name = "default_gen", sequenceName = "fx_order_seq")
@Table(
        name = "fx_order",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_fx_order_idempotency",
                        columnNames = {"idempotency_key"}
                )
        }
)
@DynamicInsert
@DynamicUpdate
public class PersistentOrder extends AuditableEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id", nullable = false)
    private PersistentAccount accountFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id")
    private PersistentAccount accountTo;

    @Column(name = "amount", nullable = false, precision = 18, scale = 4)
    private BigDecimal amount;

    @Column(name = "rate", nullable = false, precision = 18, scale = 6)
    private BigDecimal rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private OrderStatus status = OrderStatus.NEW;

    @Column(name = "idempotency_key", nullable = false, unique = true, length = 64)
    private String idempotencyKey;

    @Column(name = "failed_reason")
    private String failedReason;

    protected PersistentOrder() {
    }

    public PersistentOrder(String idempotencyKey,
                           PersistentAccount accountFrom,
                           PersistentAccount accountTo,
                           BigDecimal amount,
                           BigDecimal rate,
                           OrderStatus status) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.idempotencyKey = idempotencyKey;
        this.rate = rate;
        this.status = status;
    }

    public PersistentAccount getAccountFrom() {
        return accountFrom;
    }

    public PersistentAccount getAccountTo() {
        return accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public boolean isSuccess() {
        return OrderStatus.COMPLETED.equals(status);
    }
}