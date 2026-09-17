package com.flow.backend.controllers;

import com.flow.backend.entities.DetalleVentaEntity;
import com.flow.backend.repositories.DetalleVentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class DetalleVentaController {

    private final DetalleVentaRepository detalleVentaRepository;

    DetalleVentaController(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @PostMapping("/api/detalle_ventas")
    public ResponseEntity<DetalleVentaEntity> createDetalleVenta(@RequestBody DetalleVentaEntity createDetalleVenta){
        DetalleVentaEntity detalleVenta = detalleVentaRepository.save(createDetalleVenta);

        return new ResponseEntity<>(detalleVenta, HttpStatus.CREATED);
    }

    @GetMapping("/api/detalle_ventas")
    public ResponseEntity<List<DetalleVentaEntity>> getDetalleVentas(){
        List<DetalleVentaEntity> detalleVentas = detalleVentaRepository.findAll();

        return ResponseEntity.ok(detalleVentas);
    }

    @GetMapping("/api/detalle_ventas/{id}")
    public ResponseEntity<DetalleVentaEntity> getDetalleVenta(@PathVariable Integer id){
        Optional<DetalleVentaEntity> detalleVenta = detalleVentaRepository.findById(id);
        if (detalleVenta.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de venta invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(detalleVenta.get());
    }

    @PutMapping("/api/detalle_ventas")
    public ResponseEntity<DetalleVentaEntity> updateDetalleVenta(@RequestBody DetalleVentaEntity updateDetalleVenta){
        DetalleVentaEntity detalleVenta = detalleVentaRepository.save(updateDetalleVenta);

        return ResponseEntity.ok(detalleVenta);
    }

    @DeleteMapping("/api/detalle_ventas/{id}")
    public ResponseEntity<?> deleteDetalleVenta(@PathVariable Integer id){
        Optional<DetalleVentaEntity> detalleVenta = detalleVentaRepository.findById(id);
        if (detalleVenta.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de venta invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        detalleVentaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
