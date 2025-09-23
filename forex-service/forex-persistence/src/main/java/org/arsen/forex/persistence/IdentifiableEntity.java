package org.arsen.forex.persistence;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    protected IdentifiableEntity() {
        super();
    }

    public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id())
                .toString();
    }
}