package com.flow.backend.repositories;

import com.flow.backend.entities.PagoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoVentaRepository extends JpaRepository<PagoVenta, Integer> {
}