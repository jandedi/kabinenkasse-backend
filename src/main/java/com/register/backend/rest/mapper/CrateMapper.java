package com.register.backend.rest.mapper;

import com.register.backend.db.entities.CrateEntity;
import com.register.backend.rest.dto.CrateDto;
import org.springframework.stereotype.Component;

@Component
public class CrateMapper {

    private final PlayerMapper playerMapper;

    public CrateMapper(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    public CrateDto toDto(CrateEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CrateDto(
                entity.getId(),
                playerMapper.toDto(entity.getPlayer()),
                entity.getReason(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    /**
     * Copies DTO fields into an existing entity (create/update).
     * Does NOT set the entity id.
     *
     * Note: player relationship is set in the service (needs repository lookup).
     */
    public void updateEntityExceptPlayer(CrateEntity entity, CrateDto dto) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        if (dto == null) {
            throw new IllegalArgumentException("dto must not be null");
        }

        entity.setReason(dto.getReason());
        entity.setStatus(dto.getStatus());
    }
}