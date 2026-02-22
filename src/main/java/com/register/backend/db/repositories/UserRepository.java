package com.register.backend.db.repositories;

import com.register.backend.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    boolean existsByUsername(String username);
}