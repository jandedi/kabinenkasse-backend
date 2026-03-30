package com.register.backend.db.repositories;

import com.register.backend.db.entities.FineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<FineEntity, Integer> {
    List<FineEntity> findByIdNotNullOrderByWeightDescReasonAsc();
}