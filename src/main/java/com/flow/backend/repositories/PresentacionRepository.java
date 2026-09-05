package com.flow.backend.repositories;

import com.flow.backend.entities.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentacionRepository extends JpaRepository<Presentacion, Integer> {
}