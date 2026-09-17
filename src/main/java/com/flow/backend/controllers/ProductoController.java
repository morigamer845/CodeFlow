package com.flow.backend.controllers;

import com.flow.backend.entities.ProductoEntity;
import com.flow.backend.repositories.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class ProductoController {

    private final ProductoRepository productoRepository;

    ProductoController(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @PostMapping("/api/productos")
    public ResponseEntity<ProductoEntity> createProducto(@RequestBody ProductoEntity createProducto) {
        ProductoEntity producto = productoRepository.save(createProducto);
        
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @GetMapping("/api/productos")
    public ResponseEntity<List<ProductoEntity>> getProductos() {
        List<ProductoEntity> productos = productoRepository.findAll();
        
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/api/productos/{id}")
    public ResponseEntity<ProductoEntity> getProducto(@PathVariable Integer id) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        if (producto.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de producto invalido");
            problemDetail.setTitle("Parametro de peticion invalido");
            
            return ResponseEntity.of(problemDetail).build();
        }
        
        return ResponseEntity.ok(producto.get());
    }

    @PutMapping("/api/productos")
    public ResponseEntity<ProductoEntity> updateProducto(@RequestBody ProductoEntity updateProducto) {
        ProductoEntity producto = productoRepository.save(updateProducto);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/api/productos/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        if (producto.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de producto invalido");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        productoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
