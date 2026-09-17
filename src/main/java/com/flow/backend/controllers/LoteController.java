package com.flow.backend.controllers;

import com.flow.backend.entities.LoteEntity;
import com.flow.backend.repositories.LoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class LoteController {

    private final LoteRepository loteRepository;

    LoteController(LoteRepository loteRepository){
        this.loteRepository = loteRepository;
    }

    @PostMapping("/api/lotes")
    public ResponseEntity<LoteEntity> createLote(@RequestBody LoteEntity createLote){
        LoteEntity lote = loteRepository.save(createLote);

        return new ResponseEntity<>(lote, HttpStatus.CREATED);
    }

    @GetMapping("/api/lotes")
    public ResponseEntity<List<LoteEntity>> getLotes(){
        List<LoteEntity> lotes = loteRepository.findAll();

        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/api/lotes/{id}")
    public ResponseEntity<LoteEntity> getLote(@PathVariable Integer id){
        Optional<LoteEntity> lote = loteRepository.findById(id);
        if (lote.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de lote invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(lote.get());
    }

    @PutMapping("/api/lotes")
    public ResponseEntity<LoteEntity> updateLote(@RequestBody LoteEntity updateLote){
        LoteEntity lote = loteRepository.save(updateLote);

        return ResponseEntity.ok(lote);
    }

    @DeleteMapping("/api/lotes/{id}")
    public ResponseEntity<?> deleteLote(@PathVariable Integer id){
        Optional<LoteEntity> lote = loteRepository.findById(id);
        if (lote.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de lote invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        loteRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
