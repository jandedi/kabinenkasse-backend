package com.register.backend.rest.mapper;

import com.register.backend.db.entities.PlayerEntity;
import com.register.backend.rest.dto.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerDto toDto(PlayerEntity entity) {
        if (entity == null) {
            return null;
        }

        return new PlayerDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthday(),
                entity.getDrinkCounter()
        );
    }

    /**
     * Copies DTO fields into an existing entity (create/update).
     * Intentionally does NOT set the entity id.
     */
    public void updateEntity(PlayerEntity entity, PlayerDto dto) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        if (dto == null) {
            throw new IllegalArgumentException("dto must not be null");
        }

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthday(dto.getBirthday());
        entity.setDrinkCounter(dto.getDrinkCounter());
    }

    public PlayerEntity toNewEntity(PlayerDto dto) {
        if (dto == null) {
            return null;
        }

        PlayerEntity entity = new PlayerEntity();
        updateEntity(entity, dto);
        return entity;
    }
}