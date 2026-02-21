package com.register.backend.rest.mapper;

import com.register.backend.db.entities.PlayerFineEntity;
import com.register.backend.rest.dto.PlayerFineDto;
import org.springframework.stereotype.Component;

@Component
public class PlayerFineMapper {

    private final PlayerMapper playerMapper;

    private final FineMapper fineMapper;

    public PlayerFineMapper(PlayerMapper playerMapper, FineMapper fineMapper) {
        this.playerMapper = playerMapper;
        this.fineMapper = fineMapper;
    }

    public PlayerFineDto toDto(PlayerFineEntity entity) {
        if (entity == null) {
            return null;
        }

        return new PlayerFineDto(
                entity.getId(),
                playerMapper.toDto(entity.getPlayer()),
                fineMapper.toDto(entity.getFine()),
                entity.getStatus(),
                entity.getAmount(),
                entity.getCreatedAt()
        );
    }

    /**
     * Copies DTO fields into an existing entity (create/update).
     * Does NOT set id, player, or fine (those are resolved and set in the service).
     */
    public void updateEntityExceptRelations(PlayerFineEntity entity, PlayerFineDto dto) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        if (dto == null) {
            throw new IllegalArgumentException("dto must not be null");
        }

        entity.setStatus(dto.getStatus());
        entity.setAmount(dto.getAmount());
    }
}