package com.flow.backend.controllers;

import com.flow.backend.repositories.ProductoRepository;
import com.flow.backend.entities.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class ProductoController {
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostMapping("/api/productos")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto createProducto) {
        Producto producto = productoRepository.save(createProducto);

        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @GetMapping("/api/productos")
    public ResponseEntity<List<Producto>> getProductos() {
        List<Producto> productos = productoRepository.findAll();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/api/productos/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, " Id del Producto Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }
        return ResponseEntity.ok(producto.get());
    }

    @PutMapping("/api/productos")
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto) {
        Producto returnProducto = productoRepository.save(producto);

        return ResponseEntity.ok(returnProducto);
    }

    @DeleteMapping("/api/productos/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, " Id del Producto Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }
        productoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
