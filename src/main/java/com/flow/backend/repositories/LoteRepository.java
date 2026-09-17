package com.flow.backend.repositories;

import com.flow.backend.entities.LoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<LoteEntity, Integer> {
}