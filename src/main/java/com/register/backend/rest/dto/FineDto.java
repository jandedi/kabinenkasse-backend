package com.register.backend.rest.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.register.backend.db.entities.FineEntity}
 */
public class FineDto implements Serializable {

    private final Integer id;
    private final String reason;
    private final Float costs;

    public FineDto(Integer id, String reason, Float costs) {
        this.id = id;
        this.reason = reason;
        this.costs = costs;
    }

    public Integer getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public Float getCosts() {
        return costs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FineDto fineDto = (FineDto) o;
        return Objects.equals(id, fineDto.id)
                && Objects.equals(reason, fineDto.reason)
                && Objects.equals(costs, fineDto.costs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reason, costs);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "reason = " + reason + ", " +
                "costs = " + costs + ")";
    }
}