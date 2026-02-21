package com.register.backend.db.repositories;

import com.register.backend.db.entities.FineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<FineEntity, Integer> {
}