package com.flow.backend.controllers;

import com.flow.backend.entities.Laboratorio;
import com.flow.backend.repositories.LaboratorioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class LaboratorioController {

    private final LaboratorioRepository laboratorioRepository;

    LaboratorioController(LaboratorioRepository laboratorioRepository){
        this.laboratorioRepository = laboratorioRepository;
    }

    @PostMapping("/api/laboratorios")
    public ResponseEntity<Laboratorio> createLaboratorio(@RequestBody Laboratorio createLaboratorio){
        Laboratorio laboratorio = laboratorioRepository.save(createLaboratorio);

        return new ResponseEntity<>(laboratorio, HttpStatus.CREATED);
    }

    @GetMapping("/api/laboratorios")
    public ResponseEntity<List<Laboratorio>> getLaboratorios(){
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();

        return ResponseEntity.ok(laboratorios);
    }

    @GetMapping("/api/laboratorios/{id}")
    public ResponseEntity<Laboratorio> getLaboratorio(@PathVariable Integer id){
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
        if (laboratorio.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de laboratorio invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(laboratorio.get());
    }

    @PutMapping("/api/laboratorios")
    public ResponseEntity<Laboratorio> updateLaboratorio(@RequestBody Laboratorio updateLaboratorio){
        Laboratorio laboratorio = laboratorioRepository.save(updateLaboratorio);

        return ResponseEntity.ok(laboratorio);
    }

    @DeleteMapping("/api/laboratorios/{id}")
    public ResponseEntity<?> deleteLaboratorio(@PathVariable Integer id){
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
        if (laboratorio.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de laboratorio invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        laboratorioRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
