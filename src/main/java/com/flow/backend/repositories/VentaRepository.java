package com.flow.backend.repositories;

import com.flow.backend.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity, Integer> {
}