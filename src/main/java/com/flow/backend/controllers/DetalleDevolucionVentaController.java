package com.flow.backend.controllers;

import com.flow.backend.entities.Compra;
import com.flow.backend.entities.DetalleDevolucionVenta;
import com.flow.backend.repositories.DetalleDevolucionVentaRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class DetalleDevolucionVentaController {
    private final DetalleDevolucionVentaRepository detalleDevolucionVentaRepository;

    public DetalleDevolucionVentaController(DetalleDevolucionVentaRepository detalleDevolucionVentaRepository){
        this.detalleDevolucionVentaRepository = detalleDevolucionVentaRepository;
    }

    @PostMapping("/api/detalle_devoluciones_venta")
    public ResponseEntity<DetalleDevolucionVenta> createDetalleDevolucionVenta(@RequestBody DetalleDevolucionVenta createDetalleDevolucionVenta){
        DetalleDevolucionVenta detalleDevolucionVenta = detalleDevolucionVentaRepository.save(createDetalleDevolucionVenta);

        return new ResponseEntity<>(detalleDevolucionVenta, HttpStatus.CREATED);
    }

    @GetMapping("/api/detalle_devoluciones_venta")
    public ResponseEntity<List<DetalleDevolucionVenta>> getDetalleDevolucionesVenta(){
        List<DetalleDevolucionVenta> detalleDevolucionesVenta = detalleDevolucionVentaRepository.findAll();

        return ResponseEntity.ok(detalleDevolucionesVenta);
    }

    @GetMapping("/api/detalle_devoluciones_venta/{id}")
    public ResponseEntity<DetalleDevolucionVenta> getDetalleDevolucionVenta(@PathVariable Integer id){
        Optional<DetalleDevolucionVenta> detalleDevolucionVenta = detalleDevolucionVentaRepository.findById(id);
        if (detalleDevolucionVenta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de devolucion de compra invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(detalleDevolucionVenta.get());
    }

    @PutMapping("/api/detalle_devoluciones_venta")
    public ResponseEntity<DetalleDevolucionVenta> updateDetalleDevolucionCompra(@RequestBody DetalleDevolucionVenta updateDetalleDevolucionVenta){
        DetalleDevolucionVenta detalleDevolucionVenta = detalleDevolucionVentaRepository.save(updateDetalleDevolucionVenta);

        return ResponseEntity.ok(detalleDevolucionVenta);
    }

    @DeleteMapping("/api/detalle_devoluciones_venta/{id}")
    public ResponseEntity<?> deleteDetalleDevolucionVenta(@PathVariable Integer id){
        Optional<DetalleDevolucionVenta> detalleDevolucionVenta = detalleDevolucionVentaRepository.findById(id);
        if (detalleDevolucionVenta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de detalle de devolucion de compra invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        detalleDevolucionVentaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
