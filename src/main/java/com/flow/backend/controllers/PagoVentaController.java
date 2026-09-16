package com.flow.backend.controllers;

import com.flow.backend.entities.PagoVenta;
import com.flow.backend.repositories.PagoVentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PagoVentaController {
    private final PagoVentaRepository pagoVentaRepository;

    public PagoVentaController(PagoVentaRepository pagoVentaRepository) {
        this.pagoVentaRepository = pagoVentaRepository;
    }

    @PostMapping("/api/pagos_ventas")
    public ResponseEntity<PagoVenta> createPagoVenta(@RequestBody PagoVenta pagoVenta){
        PagoVenta pagoVentaEntity = pagoVentaRepository.save(pagoVenta);

        return new ResponseEntity<>(pagoVentaEntity, HttpStatus.CREATED);
    }


    @GetMapping("/api/pagos_ventas")
    public ResponseEntity<List<PagoVenta>> getPagosVenta(){
        List<PagoVenta> pagoVentaEntity = pagoVentaRepository.findAll();

        return ResponseEntity.ok().body(pagoVentaEntity);
    }

    @GetMapping("/api/pagos_ventas/{id}")
    public ResponseEntity<PagoVenta> getPagoVenta(@PathVariable Integer id){
        Optional<PagoVenta> pagoVentaEntity = pagoVentaRepository.findById(id);
        if(pagoVentaEntity.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, " Id del pago venta Invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }
        
        return ResponseEntity.ok().body(pagoVentaEntity.get());
    }

    @PutMapping("/api/pagos_ventas")
    public ResponseEntity<PagoVenta> updatePagoVenta(@RequestBody PagoVenta pagoVenta){
        PagoVenta returnPagoVenta = pagoVentaRepository.save(pagoVenta);

        return ResponseEntity.ok(returnPagoVenta);
    }

    @DeleteMapping("/api/pagos_ventas/{id}")
    public ResponseEntity<PagoVenta> deletePagoVenta(@PathVariable Integer id){
        Optional<PagoVenta> pagoVenta = pagoVentaRepository.findById(id);
        if(pagoVenta.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, " Id del pago venta Invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        pagoVentaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
