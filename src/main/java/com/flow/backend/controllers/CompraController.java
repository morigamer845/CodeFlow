package com.flow.backend.controllers;

import com.flow.backend.entities.CompraEntity;
import com.flow.backend.repositories.CompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class CompraController {

    private final CompraRepository compraRepository;

    CompraController(CompraRepository compraRepository){
        this.compraRepository = compraRepository;
    }

    @PostMapping("/api/compras")
    public ResponseEntity<CompraEntity> createCompra(@RequestBody CompraEntity createCompra){
        CompraEntity compra = compraRepository.save(createCompra);

        return new ResponseEntity<>(compra, HttpStatus.CREATED);
    }

    @GetMapping("/api/compras")
    public ResponseEntity<List<CompraEntity>> getCompras(){
        List<CompraEntity> compras = compraRepository.findAll();

        return ResponseEntity.ok(compras);
    }

    @GetMapping("/api/compras/{id}")
    public ResponseEntity<CompraEntity> getCompra(@PathVariable Integer id){
        Optional<CompraEntity> compra = compraRepository.findById(id);
        if(compra.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de compra invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(compra.get());
    }

    @PutMapping("/api/compras")
    public ResponseEntity<CompraEntity> updateCompra(@RequestBody CompraEntity updateCompra){
        CompraEntity compra = compraRepository.save(updateCompra);

        return ResponseEntity.ok(compra);
    }
    
    @DeleteMapping("/api/compras/{id}")
    public ResponseEntity<?> deleteCompra(@PathVariable Integer id){
        Optional<CompraEntity> compra = compraRepository.findById(id);
        if (compra.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de compra invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");
            
            return ResponseEntity.of(problemDetail).build();
        }
    
        compraRepository.deleteById(id);
        
        return ResponseEntity.ok().build();
    }
}
