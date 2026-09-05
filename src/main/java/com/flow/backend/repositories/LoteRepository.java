package com.flow.backend.repositories;

import com.flow.backend.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Integer> {
}