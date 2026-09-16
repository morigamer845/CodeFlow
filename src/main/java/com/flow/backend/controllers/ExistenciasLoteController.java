package com.flow.backend.controllers;

import com.flow.backend.entities.ExistenciasLote;
import com.flow.backend.repositories.ExistenciasLoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class ExistenciasLoteController {

    private final ExistenciasLoteRepository existenciasLoteRepository;

    ExistenciasLoteController(ExistenciasLoteRepository existenciasLoteRepository){
        this.existenciasLoteRepository = existenciasLoteRepository;
    }

    @PostMapping("/api/existencias_lotes")
    public ResponseEntity<ExistenciasLote> createExistenciasLote(@RequestBody ExistenciasLote createExistenciasLote){
        ExistenciasLote existenciasLote = existenciasLoteRepository.save(createExistenciasLote);

        return new ResponseEntity<>(existenciasLote, HttpStatus.CREATED);
    }

    @GetMapping("/api/existencias_lotes")
    public ResponseEntity<List<ExistenciasLote>> getExistenciasLotes(){
        List<ExistenciasLote> existenciasLotes = existenciasLoteRepository.findAll();

        return ResponseEntity.ok(existenciasLotes);
    }

    @GetMapping("/api/existencias_lotes/{id}")
    public ResponseEntity<ExistenciasLote> getExistenciasLote(@PathVariable Integer id){
        Optional<ExistenciasLote> existenciasLote = existenciasLoteRepository.findById(id);
        if (existenciasLote.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de existencias de lote invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(existenciasLote.get());
    }

    @PutMapping("/api/existencias_lotes")
    public ResponseEntity<ExistenciasLote> updateExistenciasLote(@RequestBody ExistenciasLote updateExistenciasLote){
        ExistenciasLote existenciasLote = existenciasLoteRepository.save(updateExistenciasLote);

        return ResponseEntity.ok(existenciasLote);
    }

    @DeleteMapping("/api/existencias_lotes/{id}")
    public ResponseEntity<?> deleteExistenciasLote(@PathVariable Integer id){
        Optional<ExistenciasLote> existenciasLote = existenciasLoteRepository.findById(id);
        if (existenciasLote.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de existencias de lote invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        existenciasLoteRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
