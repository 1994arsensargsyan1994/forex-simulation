package org.arsen.forex.simulation.persistence.rate;

import jakarta.persistence.*;
import org.arsen.forex.simulation.common.CurrencyType;
import org.arsen.forex.simulation.persistence.AuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Cacheable
@SequenceGenerator(name = "default_gen", sequenceName = "currency_rate_seq")
@Table(name = "currency_rate",
        uniqueConstraints = @UniqueConstraint(name = "UQ_currency_rate_pair", columnNames = {"currency_from", "currency_to"}))
@DynamicInsert
@DynamicUpdate
public class PersistentCurrencyRate extends AuditableEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_from", nullable = false, length = 20)
    private CurrencyType currencyFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_to", nullable = false, length = 20)
    private CurrencyType currencyTo;

    @Column(name = "rate", nullable = false, precision = 18, scale = 6)
    private BigDecimal rate;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    protected PersistentCurrencyRate() {
    }

    public PersistentCurrencyRate(CurrencyType from, CurrencyType to, BigDecimal rate, LocalDateTime lastUpdated) {
        this.currencyFrom = from;
        this.currencyTo = to;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
    }

    public CurrencyType getCurrencyFrom() {
        return currencyFrom;
    }

    public CurrencyType getCurrencyTo() {
        return currencyTo;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
