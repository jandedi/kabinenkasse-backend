package com.register.backend.rest.dto;

import com.register.backend.db.entities.PlayerFineStatusEnum;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.register.backend.db.entities.PlayerFineEntity}
 */
public class PlayerFineDto implements Serializable {

    private final Integer id;
    private final PlayerDto playerDto;
    private final FineDto fineDto;
    private final PlayerFineStatusEnum status;
    private final Byte amount;
    private final Instant createdDate;

    public PlayerFineDto(Integer id, PlayerDto playerDto, FineDto fineDto, PlayerFineStatusEnum status, Byte amount, Instant createdDate) {
        this.id = id;
        this.playerDto = playerDto;
        this.fineDto = fineDto;
        this.status = status;
        this.amount = amount;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

    public FineDto getFineDto() {
        return fineDto;
    }

    public PlayerFineStatusEnum getStatus() {
        return status;
    }

    public Byte getAmount() {
        return amount;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerFineDto that = (PlayerFineDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(playerDto, that.playerDto)
                && Objects.equals(fineDto, that.fineDto)
                && status == that.status
                && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerDto, fineDto, status, amount);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "playerId = " + playerDto + ", " +
                "fineId = " + fineDto + ", " +
                "status = " + status + ", " +
                "amount = " + amount +
                ")";
    }
}