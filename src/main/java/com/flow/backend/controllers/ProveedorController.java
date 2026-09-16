package com.flow.backend.controllers;

import com.flow.backend.entities.Proveedor;
import com.flow.backend.repositories.ProveedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProveedorController {
    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @PostMapping("/api/proveedores")
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor createProveedor) {
        Proveedor proveedor = proveedorRepository.save(createProveedor);

        return new ResponseEntity<>(proveedor, HttpStatus.CREATED);
    }

    @GetMapping("/api/proveedores")
    public ResponseEntity<List<Proveedor>> getProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();

        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/api/proveedores/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, " Id del Proveedor Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }
        return ResponseEntity.ok(proveedor.get());
    }

    @PutMapping("/api/proveedores")
    public ResponseEntity<Proveedor> updateProveedor(@RequestBody Proveedor proveedor) {
        Proveedor returnProveedor = proveedorRepository.save(proveedor);

        return ResponseEntity.ok(returnProveedor);
    }

    @DeleteMapping("/api/proveedores/{id}")
    public ResponseEntity<Proveedor> deleteProveedor(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, " Id del Proveedor Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }
        proveedorRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
