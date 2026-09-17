package com.flow.backend.repositories;

import com.flow.backend.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {
}