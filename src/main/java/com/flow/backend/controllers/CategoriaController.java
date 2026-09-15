package com.flow.backend.controllers;

import com.flow.backend.entities.Categoria;
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
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria createCategoria){
        Categoria categoria = categoriaRepository.save(createCategoria);

        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @GetMapping("/api/categorias")
    public ResponseEntity<List<Categoria>> getCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/api/categorias/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de categoria invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(categoria.get());
    }

    @PutMapping("/api/categorias")
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria updateCategoria){
        Categoria returnCategoria = categoriaRepository.save(updateCategoria);

        return ResponseEntity.ok(returnCategoria);
    }

    @DeleteMapping("/api/categorias/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de categoria invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        categoriaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
