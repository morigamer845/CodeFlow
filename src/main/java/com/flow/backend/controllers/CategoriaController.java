package com.flow.backend.controllers;

import com.flow.backend.entities.CategoriaEntity;
import com.flow.backend.repositories.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping("/api/categorias")
    public ResponseEntity<CategoriaEntity> createCategoria(@RequestBody CategoriaEntity createCategoriaEntity){
        CategoriaEntity categoria = categoriaRepository.save(createCategoriaEntity);

        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @GetMapping("/api/categorias")
    public ResponseEntity<List<CategoriaEntity>> getCategorias(){
        List<CategoriaEntity> categorias = categoriaRepository.findAll();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/api/categorias/{id}")
    public ResponseEntity<CategoriaEntity> getCategoria(@PathVariable Integer id){
        Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de categoria invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(categoria.get());
    }

    @PutMapping("/api/categorias")
    public ResponseEntity<CategoriaEntity> updateCategoria(@RequestBody CategoriaEntity updateCategoria){
        CategoriaEntity categoria = categoriaRepository.save(updateCategoria);

        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/api/categorias/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id){
        Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de categoria invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        categoriaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
