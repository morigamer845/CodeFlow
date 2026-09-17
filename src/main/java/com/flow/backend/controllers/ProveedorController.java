package com.flow.backend.controllers;

import com.flow.backend.entities.ProveedorEntity;
import com.flow.backend.repositories.ProveedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class ProveedorController {

    private final ProveedorRepository proveedorRepository;

    ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @PostMapping("/api/proveedores")
    public ResponseEntity<ProveedorEntity> createProveedor(@RequestBody ProveedorEntity createProveedor) {
        ProveedorEntity proveedor = proveedorRepository.save(createProveedor);

        return new ResponseEntity<>(proveedor, HttpStatus.CREATED);
    }

    @GetMapping("/api/proveedores")
    public ResponseEntity<List<ProveedorEntity>> getProveedores() {
        List<ProveedorEntity> proveedores = proveedorRepository.findAll();

        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/api/proveedores/{id}")
    public ResponseEntity<ProveedorEntity> getProveedor(@PathVariable Integer id) {
        Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id);
        if (proveedor.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de proveedor invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(proveedor.get());
    }

    @PutMapping("/api/proveedores")
    public ResponseEntity<ProveedorEntity> updateProveedor(@RequestBody ProveedorEntity updateProveedor) {
        ProveedorEntity proveedor = proveedorRepository.save(updateProveedor);

        return ResponseEntity.ok(proveedor);
    }

    @DeleteMapping("/api/proveedores/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable Integer id) {
        Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id);
        if (proveedor.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de proveedor invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        proveedorRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
