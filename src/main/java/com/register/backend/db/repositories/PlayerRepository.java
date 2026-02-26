package com.register.backend.db.repositories;

import com.register.backend.db.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
    List<PlayerEntity> findByOrderByFirstNameAsc();
}