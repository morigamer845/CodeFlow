package com.flow.backend.controllers;

import com.flow.backend.entities.MetodoPago;
import com.flow.backend.repositories.MetodoPagoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class MetodoPagoController {

    private final MetodoPagoRepository metodoPagoRepository;

    MetodoPagoController(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @PostMapping("/api/metodos_pago")
    public ResponseEntity<MetodoPago> createMetodoPago(@RequestBody MetodoPago createMetodoPago){
        MetodoPago metodoPago = metodoPagoRepository.save(createMetodoPago);

        return new ResponseEntity<>(metodoPago, HttpStatus.CREATED);
    }

    @GetMapping("/api/metodos_pago")
    public ResponseEntity<List<MetodoPago>> getMetodosPago(){
        List<MetodoPago> metodosPago = metodoPagoRepository.findAll();

        return ResponseEntity.ok(metodosPago);
    }

    @GetMapping("/api/metodos_pago/{id}")
    public ResponseEntity<MetodoPago> getMetodoPago(@PathVariable Integer id){
        Optional<MetodoPago> metodoPago = metodoPagoRepository.findById(id);
        if (metodoPago.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de metodo de pago invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(metodoPago.get());
    }

    @PutMapping("/api/metodos_pago")
    public ResponseEntity<MetodoPago> updateMetodoPago(@RequestBody MetodoPago updateMetodoPago){
        MetodoPago metodoPago = metodoPagoRepository.save(updateMetodoPago);

        return ResponseEntity.ok(metodoPago);
    }

    @DeleteMapping("/api/metodos_pago/{id}")
    public ResponseEntity<?> deleteMetodoPago(@PathVariable Integer id){
        Optional<MetodoPago> metodoPago = metodoPagoRepository.findById(id);
        if (metodoPago.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de metodo de pago invalida");
            problemDetail.setTitle("Parametro de peticion invalida");

            return ResponseEntity.of(problemDetail).build();
        }

        metodoPagoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
