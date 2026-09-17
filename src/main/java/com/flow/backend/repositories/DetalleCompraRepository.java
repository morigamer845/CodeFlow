package com.flow.backend.repositories;

import com.flow.backend.entities.DetalleCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompraEntity, Integer> {
}