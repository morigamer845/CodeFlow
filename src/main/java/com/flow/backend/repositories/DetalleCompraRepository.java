package com.flow.backend.repositories;

import com.flow.backend.entities.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {
}