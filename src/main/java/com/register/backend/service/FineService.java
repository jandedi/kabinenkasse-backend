package com.register.backend.service;

import com.register.backend.db.entities.FineEntity;
import com.register.backend.db.repositories.FineRepository;
import com.register.backend.rest.dto.FineDto;
import com.register.backend.rest.error.BadRequestException;
import com.register.backend.rest.error.UnknownEntityException;
import com.register.backend.rest.mapper.FineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FineService {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final FineRepository fineRepository;
    private final FineMapper fineMapper;

    public FineService(FineRepository fineRepository, FineMapper fineMapper) {
        this.fineRepository = fineRepository;
        this.fineMapper = fineMapper;
    }

    @Transactional(readOnly = true)
    public List<FineDto> getAll() {
        return fineRepository.findAll().stream()
                .map(fineMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public FineDto getById(Integer id) {
        Optional<FineEntity> entity = fineRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("Fine not found: " + id);
            throw new UnknownEntityException("Unbekannte Strafe!", "0");
        }

        return fineMapper.toDto(entity.get());
    }

    public FineDto create(FineDto body) {
        FineEntity entity = fineMapper.toNewEntity(body);
        FineEntity saved = fineRepository.save(entity);
        return fineMapper.toDto(saved);
    }

    public FineDto update(Integer id, FineDto body) {
        if (body.getId() != null && !id.equals(body.getId())) {
            throw new BadRequestException("Path id and body id must match", "0");
        }

        Optional<FineEntity> entity = fineRepository.findById(id);
        if (entity.isEmpty()) {
            logger.error("Fine not found: {}", id);
            throw new UnknownEntityException("Unbekannte Strafe!", "0");
        }

        fineMapper.updateEntity(entity.get(), body);

        FineEntity saved = fineRepository.save(entity.get());
        return fineMapper.toDto(saved);
    }

    public void delete(Integer id) {
        if (!fineRepository.existsById(id)) {
            logger.error("Fine not found: {}", id);
            throw new UnknownEntityException("Unbekannte Strafe!", "0");
        }
        fineRepository.deleteById(id);
    }

}