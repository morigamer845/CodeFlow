package com.flow.backend.repositories;

import com.flow.backend.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
}