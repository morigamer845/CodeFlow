package com.flow.backend.controllers;

import com.flow.backend.entities.DetalleCompraEntity;
import com.flow.backend.repositories.DetalleCompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class DetalleCompraController {

    private final DetalleCompraRepository detalleCompraRepository;

    DetalleCompraController(DetalleCompraRepository detalleCompraRepository){
        this.detalleCompraRepository = detalleCompraRepository;
    }
    
    @PostMapping("/api/detalle_compras")
    public ResponseEntity<DetalleCompraEntity> createDetalleCompra(@RequestBody DetalleCompraEntity createDetalleCompra){
        DetalleCompraEntity detalleCompra = detalleCompraRepository.save(createDetalleCompra);
        
        return new ResponseEntity<>(detalleCompra, HttpStatus.CREATED);
    }
    
    @GetMapping("/api/detalle_compras")
    public ResponseEntity<List<DetalleCompraEntity>> getDetalleCompras(){
        List<DetalleCompraEntity> detalleCompras = detalleCompraRepository.findAll();
        
        return ResponseEntity.ok(detalleCompras);
    }
    
    @GetMapping("/api/detalle_compras/{id}")
    public ResponseEntity<DetalleCompraEntity> getDetalleCompra(@PathVariable Integer id){
        Optional<DetalleCompraEntity> detalleCompra = detalleCompraRepository.findById(id);
        if (detalleCompra.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de compra invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");
            
            return ResponseEntity.of(problemDetail).build();
        }
        
        return ResponseEntity.ok(detalleCompra.get());
    }
    
    @PutMapping("/api/detalle_compras")
    public ResponseEntity<DetalleCompraEntity> updateDetalleCompra(@RequestBody DetalleCompraEntity updateDetalleCompra){
        DetalleCompraEntity detalleCompra = detalleCompraRepository.save(updateDetalleCompra);
        
        return ResponseEntity.ok(detalleCompra);
    }
    
    @DeleteMapping("/api/detalle_compras/{id}")
    public ResponseEntity<?> deleteDetalleCompra(@PathVariable Integer id){
        Optional<DetalleCompraEntity> detalleCompra = detalleCompraRepository.findById(id);
        if (detalleCompra.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de compra invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");
            
            return ResponseEntity.of(problemDetail).build();
        }
        
        detalleCompraRepository.deleteById(id);
        
        return ResponseEntity.ok().build();
    }
}
