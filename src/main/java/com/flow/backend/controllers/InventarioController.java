package com.flow.backend.controllers;

import com.flow.backend.entities.Inventario;
import com.flow.backend.repositories.InventarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class InventarioController {

    private final InventarioRepository inventarioRepository;

    InventarioController(InventarioRepository inventarioRepository){
        this.inventarioRepository = inventarioRepository;
    }

    @PostMapping("/api/inventarios")
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario createInventario){
        Inventario inventario = inventarioRepository.save(createInventario);

        return new ResponseEntity<>(inventario, HttpStatus.CREATED);
    }

    @GetMapping("/api/inventarios")
    public ResponseEntity<List<Inventario>> getInventarios(){
        List<Inventario> inventarios = inventarioRepository.findAll();

        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/api/inventarios/{id}")
    public ResponseEntity<Inventario> getInventario(@PathVariable Integer id){
        Optional<Inventario> inventario = inventarioRepository.findById(id);
        if (inventario.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de inventario invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(inventario.get());
    }

    @PutMapping("/api/inventarios")
    public ResponseEntity<Inventario> updateInventario(@RequestBody Inventario updateInventario){
        Inventario inventario = inventarioRepository.save(updateInventario);

        return ResponseEntity.ok(inventario);
    }

    @DeleteMapping("/api/inventarios/{id}")
    public ResponseEntity<?> deleteInventario(@PathVariable Integer id){
        Optional<Inventario> inventario = inventarioRepository.findById(id);
        if (inventario.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de inventario invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        inventarioRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
