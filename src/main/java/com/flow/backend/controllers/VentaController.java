package com.flow.backend.controllers;

import com.flow.backend.repositories.VentaRepository;
import com.flow.backend.entities.Venta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VentaController {

    private final VentaRepository ventaRepository;

    public VentaController(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @PostMapping("/api/ventas")
    public ResponseEntity<Venta> createVenta(@RequestBody Venta createVenta) {
        Venta venta = ventaRepository.save(createVenta);

        return new ResponseEntity<>(venta, HttpStatus.CREATED);
    }

    @GetMapping("/api/ventas")
    public ResponseEntity<List<Venta>> getVentas() {
        List<Venta> ventas = ventaRepository.findAll();

        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/api/ventas/{id}")
    public ResponseEntity<Venta> getVenta(@PathVariable Integer id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        if (venta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, " Id de la Venta Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(venta.get());
    }

    @PutMapping("/api/ventas")
    public ResponseEntity<Venta> updateVenta(@RequestBody Venta venta) {
        Venta returnVenta = ventaRepository.save(venta);

        return ResponseEntity.ok(returnVenta);
    }

    @DeleteMapping("/api/ventas/{id}")
    public ResponseEntity<Venta> deleteVenta(@PathVariable Integer id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        if (venta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, " Id de la Venta Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        ventaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
