package com.flow.backend.repositories;

import com.flow.backend.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
}