package com.register.backend.rest.dto;

import com.register.backend.db.entities.CrateStatusEnum;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.register.backend.db.entities.CrateEntity}
 */
public class CrateDto implements Serializable {

    private final Integer id;
    private final PlayerDto playerDto;
    private final String reason;
    private final CrateStatusEnum status;
    private final Instant createdDate;

    public CrateDto(Integer id, PlayerDto playerDto, String reason, CrateStatusEnum status, Instant createdDate) {
        this.id = id;
        this.playerDto = playerDto;
        this.reason = reason;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

    public String getReason() {
        return reason;
    }

    public CrateStatusEnum getStatus() {
        return status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrateDto crateDto = (CrateDto) o;
        return Objects.equals(id, crateDto.id)
                && Objects.equals(playerDto, crateDto.playerDto)
                && Objects.equals(reason, crateDto.reason)
                && status == crateDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerDto, reason, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "playerDto = " + playerDto + ", " +
                "reason = " + reason + ", " +
                "status = " + status + ")";
    }
}