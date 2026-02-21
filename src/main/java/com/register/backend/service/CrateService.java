package com.register.backend.service;

import com.register.backend.db.entities.CrateEntity;
import com.register.backend.db.entities.PlayerEntity;
import com.register.backend.db.repositories.CrateRepository;
import com.register.backend.db.repositories.PlayerRepository;
import com.register.backend.rest.dto.CrateDto;
import com.register.backend.rest.error.BadRequestException;
import com.register.backend.rest.error.UnknownEntityException;
import com.register.backend.rest.mapper.CrateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CrateService {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final CrateRepository crateRepository;
    private final PlayerRepository playerRepository;
    private final CrateMapper crateMapper;

    public CrateService(CrateRepository crateRepository, PlayerRepository playerRepository, CrateMapper crateMapper) {
        this.crateRepository = crateRepository;
        this.playerRepository = playerRepository;
        this.crateMapper = crateMapper;
    }

    @Transactional(readOnly = true)
    public List<CrateDto> getAll() {
        return crateRepository.findAll().stream()
                .map(crateMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public CrateDto getById(Integer id) {
        Optional<CrateEntity> entity = crateRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("Crate not found: {}", id);
            throw new UnknownEntityException("Unbekannte Kiste!", "0");
        }

        return crateMapper.toDto(entity.get());
    }

    public CrateDto create(CrateDto body) {
        if (body.getPlayerDto() == null) {
            throw new BadRequestException("Spieler darf nicht null sein.", "0");
        }

        Optional<PlayerEntity> player = playerRepository.findById(body.getPlayerDto().getId());
        if (player.isEmpty()) {
            logger.error("Player not found: {}", body.getPlayerDto().getId());
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        CrateEntity entity = new CrateEntity();
        entity.setPlayer(player.get());
        crateMapper.updateEntityExceptPlayer(entity, body);

        CrateEntity saved = crateRepository.save(entity);
        return crateMapper.toDto(saved);
    }

    public CrateDto update(Integer id, CrateDto body) {
        if (body.getId() != null && !id.equals(body.getId())) {
            throw new BadRequestException("Path id and body id must match", "0");
        }

        if (body.getPlayerDto() == null) {
            throw new BadRequestException("Spieler darf nicht null sein.", "0");
        }

        Optional<CrateEntity> entity = crateRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("Crate not found: {}", id);
            throw new UnknownEntityException("Unbekannte Kiste!", "0");
        }

        Optional<PlayerEntity> player = playerRepository.findById(body.getPlayerDto().getId());
        if (player.isEmpty()) {
            logger.error("Player not found: {}", body.getPlayerDto().getId());
            throw new UnknownEntityException("Unbekannter Spieler!", "0");
        }

        entity.get().setPlayer(player.get());
        crateMapper.updateEntityExceptPlayer(entity.get(), body);

        CrateEntity saved = crateRepository.save(entity.get());
        return crateMapper.toDto(saved);
    }

    public void delete(Integer id) {
        if (!crateRepository.existsById(id)) {
            logger.error("Crate not found: {}", id);
            throw new UnknownEntityException("Unbekannte Kiste!", "0");
        }
        crateRepository.deleteById(id);
    }
}