package com.flow.backend.repositories;

import com.flow.backend.entities.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
}