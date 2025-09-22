package org.arsen.forex.simulation.persistence.account;

import jakarta.persistence.*;
import org.arsen.forex.simulation.common.CurrencyType;
import org.arsen.forex.simulation.persistence.AuditableEntity;
import org.arsen.forex.simulation.persistence.customer.PersistentCustomer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Cacheable
@SequenceGenerator(name = "default_gen", sequenceName = "account_seq")
@Table(name = "account")
@DynamicInsert
@DynamicUpdate
public class PersistentAccount extends AuditableEntity {

    @Column(name = "number", nullable = false, unique = true, length = 50)
    private String number;

    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @Column(name = "is_disabled", nullable = false)
    private boolean isDisabled = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false, length = 20)
    private CurrencyType currency;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private PersistentCustomer customer;

    /* Will be used by reflection */
    protected PersistentAccount() {
        super();
    }

    public PersistentAccount(String number, CurrencyType currency, PersistentCustomer customer) {
        this.number = number;
        this.currency = currency;
        this.customer = customer;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public PersistentCustomer getCustomer() {
        return customer;
    }

    public void debit(BigDecimal amount) {
        Assert.notNull(amount, "amount must not be null");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        Assert.notNull(amount, "amount must not be null");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }
        this.balance = this.balance.add(amount);
    }

}
