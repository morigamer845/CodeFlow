package com.flow.backend.repositories;

import com.flow.backend.entities.DetalleDevolucionVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleDevolucionVentaRepository extends JpaRepository<DetalleDevolucionVenta, Integer> {
}