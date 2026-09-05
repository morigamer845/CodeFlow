package com.flow.backend.repositories;

import com.flow.backend.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}