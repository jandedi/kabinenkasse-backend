package com.register.backend.rest.mapper;

import com.register.backend.db.entities.FineEntity;
import com.register.backend.rest.dto.FineDto;
import org.springframework.stereotype.Component;

@Component
public class FineMapper {

    public FineDto toDto(FineEntity entity) {
        if (entity == null) {
            return null;
        }
        return new FineDto(
                entity.getId(),
                entity.getReason(),
                entity.getCosts()
        );
    }

    /**
     * Copies DTO fields into an existing entity (create/update).
     * Intentionally does NOT set the entity id.
     */
    public void updateEntity(FineEntity entity, FineDto dto) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        if (dto == null) {
            throw new IllegalArgumentException("dto must not be null");
        }

        entity.setReason(dto.getReason());
        entity.setCosts(dto.getCosts());
    }

    public FineEntity toNewEntity(FineDto dto) {
        if (dto == null) {
            return null;
        }
        FineEntity entity = new FineEntity();
        updateEntity(entity, dto);
        return entity;
    }
}
