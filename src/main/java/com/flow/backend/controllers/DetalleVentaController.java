package com.flow.backend.controllers;

import com.flow.backend.entities.DetalleVenta;
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

    public DetalleVentaController(DetalleVentaRepository detalleVentaRepository){
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @PostMapping("/api/detalle_ventas")
    public ResponseEntity<DetalleVenta> createDetalleVenta(@RequestBody DetalleVenta createDetalleVenta){
        DetalleVenta detalleVenta = detalleVentaRepository.save(createDetalleVenta);

        return new ResponseEntity<>(detalleVenta, HttpStatus.CREATED);
    }

    @GetMapping("/api/detalle_ventas")
    public ResponseEntity<List<DetalleVenta>> getDetalleVentas(){
        List<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();

        return ResponseEntity.ok(detalleVentas);
    }

    @GetMapping("/api/detalle_ventas/{id}")
    public ResponseEntity<DetalleVenta> getDetalleVenta(@PathVariable Integer id){
        Optional<DetalleVenta> detalleVenta = detalleVentaRepository.findById(id);
        if (detalleVenta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de venta invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(detalleVenta.get());
    }

    @PutMapping("/api/detalle_ventas")
    public ResponseEntity<DetalleVenta> updateDetalleVenta(@RequestBody DetalleVenta updateDetalleVenta){
        DetalleVenta detalleVenta = detalleVentaRepository.save(updateDetalleVenta);

        return ResponseEntity.ok(detalleVenta);
    }

    @DeleteMapping("/api/detalle_ventas/{id}")
    public ResponseEntity<?> deleteDetalleVenta(@PathVariable Integer id){
        Optional<DetalleVenta> detalleVenta = detalleVentaRepository.findById(id);
        if (detalleVenta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de venta invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        detalleVentaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
