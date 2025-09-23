package org.arsen.forex.persistence.address;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.persistence.AuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Cacheable
@SequenceGenerator(name = "default_gen", sequenceName = "address_seq")
@Table(name = "address")
@DynamicInsert
@DynamicUpdate
public class PersistentAddress extends AuditableEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "zipCode", length = 20)
    private String zipCode;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    /* Will be used by reflection */
    protected PersistentAddress() {
        super();
    }

    public PersistentAddress(String street, String city, String state, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("street", getStreet())
                .append("city", getCity())
                .append("state", getState())
                .append("zipCode", getZipCode())
                .append("country", getCountry())
                .toString();
    }
}
