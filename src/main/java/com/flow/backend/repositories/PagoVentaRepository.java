package com.flow.backend.repositories;

import com.flow.backend.entities.PagoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagoVentaRepository extends JpaRepository<PagoVenta, Integer> {
    @Override
    Optional<PagoVenta> findById(Integer integer);
}