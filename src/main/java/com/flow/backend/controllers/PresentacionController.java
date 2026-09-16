package com.flow.backend.controllers;

import com.flow.backend.repositories.PresentacionRepository;
import com.flow.backend.entities.Presentacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PresentacionController {

    private final PresentacionRepository presentacionRepository;

    public PresentacionController(PresentacionRepository presentacionRepository) {
        this.presentacionRepository = presentacionRepository;
    }

    @PostMapping("/api/presentaciones")
    public ResponseEntity<Presentacion> createPresentacion(@RequestBody Presentacion createPresentacion) {
        Presentacion presentacion = presentacionRepository.save(createPresentacion);

        return new ResponseEntity<>(presentacion, HttpStatus.CREATED);
    }

    @GetMapping("/api/presentaciones")
    public ResponseEntity<List<Presentacion>> getPresentaciones() {
        List<Presentacion> presentaciones = presentacionRepository.findAll();

        return ResponseEntity.ok(presentaciones);
    }

    @GetMapping("/api/presentaciones/{id}")
    public ResponseEntity<Presentacion> getPresentacion(@PathVariable Integer id) {
        Optional<Presentacion> presentacion = presentacionRepository.findById(id);
        if (presentacion.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Id de la presentacion Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(presentacion.get());
    }

    @PutMapping("/api/presentaciones")
    public ResponseEntity<Presentacion> updatePresentacion(@RequestBody Presentacion presentacion) {
        Presentacion returnPresentacion = presentacionRepository.save(presentacion);

        return ResponseEntity.ok(returnPresentacion);
    }

    @DeleteMapping("/api/presentaciones/{id}")
    public ResponseEntity<Presentacion> deletePresentacion(@PathVariable Integer id) {
        Optional<Presentacion> presentacion = presentacionRepository.findById(id);
        if (presentacion.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de la presentacion Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        presentacionRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
