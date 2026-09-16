package com.flow.backend.controllers;

import com.flow.backend.entities.DevolucionVenta;
import com.flow.backend.repositories.DevolucionVentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class DevolucionVentaController {

    private final DevolucionVentaRepository devolucionVentaRepository;

    DevolucionVentaController(DevolucionVentaRepository devolucionVentaRepository){
        this.devolucionVentaRepository = devolucionVentaRepository;
    }

    @PostMapping("/api/devoluciones_venta")
    public ResponseEntity<DevolucionVenta> createDevolucionVenta(@RequestBody DevolucionVenta createDevolucionVenta){
        DevolucionVenta devolucionVenta = devolucionVentaRepository.save(createDevolucionVenta);

        return new ResponseEntity<>(devolucionVenta, HttpStatus.CREATED);
    }

    @GetMapping("/api/devoluciones_venta")
    public ResponseEntity<List<DevolucionVenta>> getDevolucionesVenta(){
        List<DevolucionVenta> devolucionesVenta = devolucionVentaRepository.findAll();

        return ResponseEntity.ok(devolucionesVenta);
    }

    @GetMapping("/api/devoluciones_venta/{id}")
    public ResponseEntity<DevolucionVenta> getDevolucionVenta(@PathVariable Integer id){
        Optional<DevolucionVenta> devolucionVenta = devolucionVentaRepository.findById(id);
        if (devolucionVenta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de devolucion de venta invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(devolucionVenta.get());
    }

    @PutMapping("/api/devoluciones_venta")
    public ResponseEntity<DevolucionVenta> updateDevolucionVenta(@RequestBody DevolucionVenta updateDevolucionVenta){
        DevolucionVenta devolucionVenta = devolucionVentaRepository.save(updateDevolucionVenta);

        return ResponseEntity.ok(devolucionVenta);
    }

    @DeleteMapping("/api/devoluciones_venta/{id}")
    public ResponseEntity<?> deleteDevolucionVenta(@PathVariable Integer id){
        Optional<DevolucionVenta> devolucionVenta = devolucionVentaRepository.findById(id);
        if (devolucionVenta.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de devolucion de venta invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        devolucionVentaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
