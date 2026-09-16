package com.flow.backend.controllers;

import com.flow.backend.repositories.MovimientoInventarioRepository;
import com.flow.backend.entities.MovimientoInventario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovimientoInventarioController {

    private final MovimientoInventarioRepository movimientoInventarioRepository;

    public MovimientoInventarioController(MovimientoInventarioRepository movimientoInventarioRepository) {
        this.movimientoInventarioRepository = movimientoInventarioRepository;
    }

    @PostMapping("/api/movimientos_inventario")
    public ResponseEntity<MovimientoInventario> createMovimientoInventario(@RequestBody MovimientoInventario createMovimientoInventario){
        MovimientoInventario movimienentoInventario = movimientoInventarioRepository.save(createMovimientoInventario);

        return new ResponseEntity<>(movimienentoInventario, HttpStatus.CREATED);
    }

    @GetMapping("/api/movimientos_inventario")
    public ResponseEntity<List<MovimientoInventario>> getMovimientoInventario(){
        List<MovimientoInventario> movimientosInventarios = movimientoInventarioRepository.findAll();

        return ResponseEntity.ok(movimientosInventarios);
    }

    @GetMapping("/api/movimientos_inventario/{id}")
    public ResponseEntity<MovimientoInventario> getMovimientoInventario(@PathVariable Integer id){
        Optional<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findById(id);
        if(movimientoInventario.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, " Id del Movimiento inventario Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(movimientoInventario.get());
    }

    @PutMapping("/api/movimientos_inventario")
    public ResponseEntity<MovimientoInventario> updateMovimientoInventario(@RequestBody MovimientoInventario movimientoInventario){
        MovimientoInventario returnMovimientoInventario = movimientoInventarioRepository.save(movimientoInventario);

        return ResponseEntity.ok(returnMovimientoInventario);
    }

    @DeleteMapping("/api/movimientos_inventario/{id}")
    public ResponseEntity<MovimientoInventario> deleteMovimientoInventario(@PathVariable Integer id){
        Optional<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findById(id);
        if(movimientoInventario.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, " Id del Movimiento inventario Invalido");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        movimientoInventarioRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
