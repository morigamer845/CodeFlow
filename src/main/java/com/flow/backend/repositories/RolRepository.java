package com.flow.backend.repositories;

import com.flow.backend.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}