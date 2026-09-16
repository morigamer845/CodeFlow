package com.flow.backend.controllers;

import com.flow.backend.entities.DetalleCompra;
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

    public DetalleCompraController(DetalleCompraRepository detalleCompraRepository){
        this.detalleCompraRepository = detalleCompraRepository;
    }

    @PostMapping("/api/detalle_compras")
    public ResponseEntity<DetalleCompra> createDetalleCompra(@RequestBody DetalleCompra createDetalleCompra){
        DetalleCompra detalleCompra = detalleCompraRepository.save(createDetalleCompra);

        return new ResponseEntity<>(detalleCompra, HttpStatus.CREATED);
    }

    @GetMapping("/api/detalle_compras")
    public ResponseEntity<List<DetalleCompra>> getDetalleCompra(){
        List<DetalleCompra> detallesCompras = detalleCompraRepository.findAll();

        return ResponseEntity.ok(detallesCompras);
    }

    @GetMapping("/api/detalle_compras/{id}")
    public ResponseEntity<DetalleCompra> getDetalleCompra(@PathVariable Integer id){
        Optional<DetalleCompra> detalleCompra = detalleCompraRepository.findById(id);
        if (detalleCompra.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de compra invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(detalleCompra.get());
    }

    @PutMapping("/api/detalle_compras")
    public ResponseEntity<DetalleCompra> updateDetalleCompra(@RequestBody DetalleCompra updateDetalleCompra){
        DetalleCompra detalleCompra = detalleCompraRepository.save(updateDetalleCompra);

        return ResponseEntity.ok(detalleCompra);
    }

    @DeleteMapping("/api/detalle_compras/{id}")
    public ResponseEntity<?> deleteDetalleCompra(@PathVariable Integer id){
        Optional<DetalleCompra> detalleCompra = detalleCompraRepository.findById(id);
        if (detalleCompra.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de compra invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        detalleCompraRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
