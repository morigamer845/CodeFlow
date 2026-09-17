package com.flow.backend.repositories;

import com.flow.backend.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
}