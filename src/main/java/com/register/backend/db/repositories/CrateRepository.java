package com.register.backend.db.repositories;

import com.register.backend.db.entities.CrateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrateRepository extends JpaRepository<CrateEntity, Integer> {
}