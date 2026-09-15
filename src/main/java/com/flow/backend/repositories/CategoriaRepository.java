package com.flow.backend.repositories;

import com.flow.backend.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Override
    Optional<Categoria> findById(Integer integer);
}