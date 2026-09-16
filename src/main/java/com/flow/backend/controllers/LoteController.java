package com.flow.backend.controllers;

import com.flow.backend.entities.Lote;
import com.flow.backend.repositories.LoteRepository;
import org.apache.coyote.Response;
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
    public ResponseEntity<Lote> createLote(@RequestBody Lote createLote){
        Lote lote = loteRepository.save(createLote);

        return new ResponseEntity<>(lote, HttpStatus.CREATED);
    }

    @GetMapping("/api/lotes")
    public ResponseEntity<List<Lote>> getLotes(){
        List<Lote> lotes = loteRepository.findAll();

        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/api/lotes/{id}")
    public ResponseEntity<Lote> getLote(@PathVariable Integer id){
        Optional<Lote> lote = loteRepository.findById(id);
        if(lote.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de lote invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(lote.get());
    }

    @PutMapping("/api/lotes")
    public ResponseEntity<Lote> updateLote(@RequestBody Lote updateLote){
        Lote lote = loteRepository.save(updateLote);

        return ResponseEntity.ok(lote);
    }

    @DeleteMapping("/api/lotes/{id}")
    public ResponseEntity<?> deleteLote(@PathVariable Integer id){
        Optional<Lote> lote = loteRepository.findById(id);
        if(lote.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de lote invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        loteRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
