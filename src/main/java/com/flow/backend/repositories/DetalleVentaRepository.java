package com.flow.backend.repositories;

import com.flow.backend.entities.DetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Integer> {
}