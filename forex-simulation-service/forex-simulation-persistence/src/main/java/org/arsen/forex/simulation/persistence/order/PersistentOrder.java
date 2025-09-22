package org.arsen.forex.simulation.persistence.order;

import jakarta.persistence.*;
import org.arsen.forex.simulation.common.CurrencyType;
import org.arsen.forex.simulation.persistence.AuditableEntity;
import org.arsen.forex.simulation.persistence.account.PersistentAccount;
import org.arsen.forex.simulation.persistence.common.OrderStatus;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Cacheable
@SequenceGenerator(name = "default_gen", sequenceName = "fx_order_seq")
@Table(name = "fx_order")
@DynamicInsert
@DynamicUpdate
public class PersistentOrder extends AuditableEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id", nullable = false)
    private PersistentAccount accountFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id") // optional—if you credit a target currency account
    private PersistentAccount accountTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_from", nullable = false, length = 20)
    private CurrencyType currencyFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_to", nullable = false, length = 20)
    private CurrencyType currencyTo;

    @Column(name = "amount", nullable = false, precision = 18, scale = 4)
    private BigDecimal amount;

    @Column(name = "rate", nullable = false, precision = 18, scale = 6)
    private BigDecimal rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private OrderStatus status = OrderStatus.NEW;

    protected PersistentOrder() { }

    public PersistentOrder(PersistentAccount accountFrom,
                           PersistentAccount accountTo,
                           CurrencyType currencyFrom,
                           CurrencyType currencyTo,
                           BigDecimal amount,
                           BigDecimal rate) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.rate = rate;
        this.status = OrderStatus.NEW;
    }

    // getters...
}