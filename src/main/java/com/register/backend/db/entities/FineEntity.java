package com.register.backend.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Fine")
public class FineEntity extends AbstractParentEntity {

    @ColumnDefault("''")
    @Column(name = "Reason", nullable = false, length = 100)
    private String reason;

    @ColumnDefault("1")
    @Column(name = "Costs", nullable = false)
    private Float costs;

    @Column(name = "Weight")
    private Integer weight;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Float getCosts() {
        return costs;
    }

    public void setCosts(Float costs) {
        this.costs = costs;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}