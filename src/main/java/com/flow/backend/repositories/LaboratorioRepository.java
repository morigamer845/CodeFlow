package com.flow.backend.repositories;

import com.flow.backend.entities.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {
}