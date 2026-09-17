package com.flow.backend.controllers;

import com.flow.backend.entities.ClienteEntity;
import com.flow.backend.repositories.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
class ClienteController {

    private final ClienteRepository clienteRepository;

    ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/api/clientes")
    public ResponseEntity<ClienteEntity> createCliente(@RequestBody ClienteEntity createCliente){
        ClienteEntity cliente = clienteRepository.save(createCliente);

        return new ResponseEntity<>(cliente, HttpStatus.CREATED );
    }

    @GetMapping("/api/clientes")
    public ResponseEntity<List<ClienteEntity>> getClientes(){
        List<ClienteEntity> clientes = clienteRepository.findAll();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/api/clientes/{id}")
    public ResponseEntity<ClienteEntity> getCliente(@PathVariable Integer id){
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de cliente invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        return ResponseEntity.ok(cliente.get());
    }

    @PutMapping("/api/clientes")
    public ResponseEntity<ClienteEntity> updateCliente(@RequestBody ClienteEntity updateCliente){
        ClienteEntity cliente = clienteRepository.save(updateCliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/api/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id){
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()){
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de cliente invalido!");
            problemDetail.setTitle("Parametro de peticion invalido");

            return ResponseEntity.of(problemDetail).build();
        }

        clienteRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
