package com.flow.backend.repositories;

import com.flow.backend.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}