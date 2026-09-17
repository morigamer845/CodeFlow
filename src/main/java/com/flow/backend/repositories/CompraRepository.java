package com.flow.backend.repositories;

import com.flow.backend.entities.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<CompraEntity, Integer> {
}