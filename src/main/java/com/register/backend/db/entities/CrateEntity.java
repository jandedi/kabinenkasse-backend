package com.register.backend.db.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Crate")
public class CrateEntity extends AbstractParentEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @ColumnDefault("0")
    @JoinColumn(name = "PlayerId", nullable = false)
    private PlayerEntity player;

    @ColumnDefault("'0'")
    @Column(name = "Reason", nullable = false)
    private String reason;

    @ColumnDefault("'NEW'")
    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CrateStatusEnum status;

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CrateStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CrateStatusEnum status) {
        this.status = status;
    }

}