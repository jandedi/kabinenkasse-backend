package com.register.backend.service;

import com.register.backend.db.entities.FineEntity;
import com.register.backend.db.entities.PlayerEntity;
import com.register.backend.db.entities.PlayerFineEntity;
import com.register.backend.db.repositories.FineRepository;
import com.register.backend.db.repositories.PlayerFineRepository;
import com.register.backend.db.repositories.PlayerRepository;
import com.register.backend.rest.dto.PlayerFineDto;
import com.register.backend.rest.error.BadRequestException;
import com.register.backend.rest.error.UnknownEntityException;
import com.register.backend.rest.mapper.PlayerFineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerFineService {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PlayerFineRepository playerFineRepository;
    private final PlayerRepository playerRepository;
    private final FineRepository fineRepository;
    private final PlayerFineMapper playerFineMapper;

    public PlayerFineService(
            PlayerFineRepository playerFineRepository,
            PlayerRepository playerRepository,
            FineRepository fineRepository,
            PlayerFineMapper playerFineMapper
    ) {
        this.playerFineRepository = playerFineRepository;
        this.playerRepository = playerRepository;
        this.fineRepository = fineRepository;
        this.playerFineMapper = playerFineMapper;
    }

    @Transactional(readOnly = true)
    public List<PlayerFineDto> getAll() {
        return playerFineRepository.findAll().stream()
                .map(playerFineMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerFineDto getById(Integer id) {
        Optional<PlayerFineEntity> entity = playerFineRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("PlayerFine not found: " + id);
            throw new UnknownEntityException("Unbekannte Spielerstrafe!", "0");
        }

        return playerFineMapper.toDto(entity.get());
    }

    public PlayerFineDto create(PlayerFineDto body) {
        if (body.getPlayerDto() == null) {
            throw new BadRequestException("Spieler darf nicht null sein.", "0");
        }

        if (body.getFineDto() == null) {
            throw new BadRequestException("Strafe darf nicht null sein.", "0");
        }

        Optional<PlayerEntity> player = playerRepository.findById(body.getPlayerDto().getId());
        if (player.isEmpty()) {
            logger.error("Player not found: {}", body.getPlayerDto());
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        Optional<FineEntity> fine = fineRepository.findById(body.getFineDto().getId());
        if (fine.isEmpty()) {
            logger.error("Fine not found: {}", body.getFineDto());
            throw new UnknownEntityException("Unbekannte Strafe!", "0");
        }

        PlayerFineEntity entity = new PlayerFineEntity();
        entity.setPlayer(player.get());
        entity.setFine(fine.get());
        playerFineMapper.updateEntityExceptRelations(entity, body);

        PlayerFineEntity saved = playerFineRepository.save(entity);
        return playerFineMapper.toDto(saved);
    }

    public PlayerFineDto update(Integer id, PlayerFineDto body) {
        if (body.getId() != null && !id.equals(body.getId())) {
            throw new BadRequestException("Path id and body id must match", "0");
        }

        if (body.getPlayerDto() == null) {
            throw new BadRequestException("Spieler darf nicht null sein.", "0");
        }
        if (body.getFineDto() == null) {
            throw new BadRequestException("Strafe darf nicht null sein.", "0");
        }

        Optional<PlayerFineEntity> entity = playerFineRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("PlayerFine not found: {}", id);
            throw new UnknownEntityException("Unbekannte Spielerstrafe!", "0");
        }

        Optional<PlayerEntity> player = playerRepository.findById(body.getPlayerDto().getId());
        if (player.isEmpty()) {
            logger.error("Player not found: {}", body.getPlayerDto());
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        Optional<FineEntity> fine = fineRepository.findById(body.getFineDto().getId());
        if (fine.isEmpty()) {
            logger.error("Fine not found: {}", body.getFineDto());
            throw new UnknownEntityException("Unbekannte Strafe!", "0");
        }

        entity.get().setPlayer(player.get());
        entity.get().setFine(fine.get());
        playerFineMapper.updateEntityExceptRelations(entity.get(), body);

        PlayerFineEntity saved = playerFineRepository.save(entity.get());
        return playerFineMapper.toDto(saved);
    }

    public void delete(Integer id) {
        if (!playerFineRepository.existsById(id)) {
            logger.error("PlayerFine not found: {}", id);
            throw new UnknownEntityException("Unbekannte Spielerstrafe!", "0");
        }
        playerFineRepository.deleteById(id);
    }
}