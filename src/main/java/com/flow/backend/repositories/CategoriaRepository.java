package com.flow.backend.repositories;

import com.flow.backend.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}