package com.register.backend.db.repositories;

import com.register.backend.db.entities.PlayerFineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerFineRepository extends JpaRepository<PlayerFineEntity, Integer> {
}