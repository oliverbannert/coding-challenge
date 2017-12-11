package de.coding.core;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    void setVersion(Long version) {
        this.version = version;
    }

    @PrePersist
    void prePersist() {
        created = new Date(System.currentTimeMillis());
    }

    public Date getCreated() {
        return created;
    }

    @VisibleForTesting
    public void setCreated(Date created) {
        this.created = created;
    }
}
