package com.register.backend.service;

import com.register.backend.db.entities.PlayerEntity;
import com.register.backend.db.repositories.PlayerRepository;
import com.register.backend.rest.dto.PlayerDto;
import com.register.backend.rest.error.BadRequestException;
import com.register.backend.rest.error.UnknownEntityException;
import com.register.backend.rest.mapper.PlayerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerService {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Transactional(readOnly = true)
    public List<PlayerDto> getAll() {
        return playerRepository.findByOrderByFirstNameAsc().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerDto getById(Integer id) {
        Optional<PlayerEntity> entity = playerRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("Player not found: {}", id);
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        return playerMapper.toDto(entity.get());
    }

    public PlayerDto create(PlayerDto body) {
        PlayerEntity entity = playerMapper.toNewEntity(body);
        PlayerEntity saved = playerRepository.save(entity);
        return playerMapper.toDto(saved);
    }

    public PlayerDto update(Integer id, PlayerDto body) {
        if (body.getId() != null && !id.equals(body.getId())) {
            throw new BadRequestException("Path id and body id must match", "0");
        }

        Optional<PlayerEntity> entity = playerRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("Player not found: {}", id);
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        playerMapper.updateEntity(entity.get(), body);

        PlayerEntity saved = playerRepository.save(entity.get());
        return playerMapper.toDto(saved);
    }

    public void delete(Integer id) {
        if (!playerRepository.existsById(id)) {
            logger.error("Player not found: {}", id);
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }
        playerRepository.deleteById(id);
    }

    public PlayerDto incrementDrinkCounter(Integer id) {
        Optional<PlayerEntity> entity = playerRepository.findById(id);
        if (entity.isEmpty()){
            logger.error("Player not found: {}", id);
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        logger.info("Player '{}' drink counter increased by 1", entity.get().getFirstName());

        int current = entity.get().getDrinkCounter() == null ? 0 : entity.get().getDrinkCounter();
        entity.get().setDrinkCounter(current + 1);

        PlayerEntity saved = playerRepository.save(entity.get());
        return playerMapper.toDto(saved);
    }

    public PlayerDto decrementDrinkCounter(Integer id) {
        Optional<PlayerEntity> entity = playerRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("Player not found: {}", id);
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        logger.info("Player '{}' drink counter decreased by 1", entity.get().getFirstName());

        int current = entity.get().getDrinkCounter() == null ? 0 : entity.get().getDrinkCounter();

        entity.get().setDrinkCounter(current - 1);

        PlayerEntity saved = playerRepository.save(entity.get());
        return playerMapper.toDto(saved);
    }
}