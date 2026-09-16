package com.flow.backend.controllers;

import com.flow.backend.entities.UnidadMedida;
import com.flow.backend.repositories.UnidadMedidaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UnidadMedidaController {

    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaController(UnidadMedidaRepository unidadMedidaRepository) {
        this.unidadMedidaRepository = unidadMedidaRepository;
    }

    @PostMapping("/api/unidades_medida")
    public ResponseEntity<UnidadMedida> createUnidadMedida(@RequestBody UnidadMedida createUnidadMedida) {
        UnidadMedida unidadMedida = unidadMedidaRepository.save(createUnidadMedida);

        return new ResponseEntity<>(unidadMedida, HttpStatus.CREATED);
    }

    @GetMapping("/api/unidades_medida")
    public ResponseEntity<List<UnidadMedida>> getUnidadesMedida() {
        List<UnidadMedida> unidadesMedida = unidadMedidaRepository.findAll();

        return ResponseEntity.ok(unidadesMedida);
    }

    @GetMapping("/api/unidades_medida/{id}")
    public ResponseEntity<UnidadMedida> getUnidadMedida(@PathVariable Integer id) {
        Optional<UnidadMedida> unidadMedida = unidadMedidaRepository.findById(id);
        if (unidadMedida.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, " Id de la Unidad de Medida Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(unidadMedida.get());
    }

    @PutMapping("/api/unidades_medida")
    public ResponseEntity<UnidadMedida> updateUnidadMedida(@RequestBody UnidadMedida unidadMedida) {
        UnidadMedida returnUnidadMedida = unidadMedidaRepository.save(unidadMedida);

        return ResponseEntity.ok(returnUnidadMedida);
    }

    @DeleteMapping("/api/unidades_medida/{id}")
    public ResponseEntity<UnidadMedida> deleteUnidadMedida(@PathVariable Integer id) {
        Optional<UnidadMedida> unidadMedida = unidadMedidaRepository.findById(id);
        if (unidadMedida.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, " Id de la Unidad de Medida Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        unidadMedidaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
