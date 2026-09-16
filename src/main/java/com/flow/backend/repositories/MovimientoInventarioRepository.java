package com.flow.backend.repositories;

import com.flow.backend.entities.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
    @Override
    Optional<MovimientoInventario> findById(Integer integer);
}