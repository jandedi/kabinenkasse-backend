package com.register.backend.db.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ColumnDefault("''")
    @Column(name = "CreatedBy", nullable = false, length = 100)
    @CreatedBy
    private String createdBy = "Test";

    @ColumnDefault("current_timestamp()")
    @Column(name = "CreatedAt", nullable = false)
    @CreatedDate
    private Instant createdAt;

    @ColumnDefault("''")
    @Column(name = "LastModifiedBy", nullable = false, length = 100)
    @LastModifiedBy
    private String lastModifiedBy = "Test";

    @ColumnDefault("current_timestamp()")
    @Column(name = "LastModifiedAt", nullable = false)
    @LastModifiedDate
    private Instant lastModifiedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
