package ecom.clothes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public abstract class Timestamp  {

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created_at;

    @Column(name = "updated_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updated_at;
}
