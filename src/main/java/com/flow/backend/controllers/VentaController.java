package com.flow.backend.controllers;

import com.flow.backend.entities.VentaEntity;
import com.flow.backend.repositories.VentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class VentaController {

    private final VentaRepository ventaRepository;

    VentaController(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @PostMapping("/api/ventas")
    public ResponseEntity<VentaEntity> createVenta(@RequestBody VentaEntity createVenta) {
        VentaEntity venta = ventaRepository.save(createVenta);

        return new ResponseEntity<>(venta, HttpStatus.CREATED);
    }

    @GetMapping("/api/ventas")
    public ResponseEntity<List<VentaEntity>> getVentas() {
        List<VentaEntity> ventas = ventaRepository.findAll();

        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/api/ventas/{id}")
    public ResponseEntity<VentaEntity> getVenta(@PathVariable Integer id) {
        Optional<VentaEntity> venta = ventaRepository.findById(id);
        if(venta.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de venta invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(venta.get());
    }

    @PutMapping("/api/ventas")
    public ResponseEntity<VentaEntity> updateVenta(@RequestBody VentaEntity updateVenta) {
        VentaEntity venta = ventaRepository.save(updateVenta);

        return ResponseEntity.ok(venta);
    }

    @DeleteMapping("/api/ventas/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Integer id) {
        Optional<VentaEntity> venta = ventaRepository.findById(id);
        if(venta.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de venta invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        ventaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
