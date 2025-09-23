package org.arsen.forex.persistence.customer;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.persistence.AuditableEntity;
import org.arsen.forex.persistence.account.PersistentAccount;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Cacheable
@SequenceGenerator(name = "default_gen", sequenceName = "customer_seq")
@Table(name = "customer")
@DynamicInsert
@DynamicUpdate
public class PersistentCustomer extends AuditableEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersistentAccount> accounts;

    /* Will be used by reflection */
    protected PersistentCustomer() {
        super();
    }

    public PersistentCustomer(String username, String email, LocalDate dateOfBirth) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", getUsername())
                .append("email", getEmail())
                .append("dateOfBirth", getDateOfBirth())
                .toString();
    }
}
