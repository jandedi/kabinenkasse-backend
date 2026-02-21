package com.register.backend.db.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "PlayerFine")
public class PlayerFineEntity extends AbstractParentEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PlayerId", nullable = false)
    private PlayerEntity player;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "FineId", nullable = false)
    private FineEntity fine;

    @ColumnDefault("'NEW'")
    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PlayerFineStatusEnum status;

    @ColumnDefault("1")
    @Column(name = "Amount", nullable = false)
    private Byte amount;

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public FineEntity getFine() {
        return fine;
    }

    public void setFine(FineEntity fine) {
        this.fine = fine;
    }

    public PlayerFineStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PlayerFineStatusEnum status) {
        this.status = status;
    }

    public Byte getAmount() {
        return amount;
    }

    public void setAmount(Byte amount) {
        this.amount = amount;
    }

}