package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.Cliente;
import com.example.demo.service.ClienteService;
import lombok.RequiredArgsConstructor;


// Manejo de endpoints.

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@CrossOrigin("*") // Autoriza comunicaciones no seguras.
public class ClienteController {
    
    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> buscarTodos(){
        return clienteService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente c){
        return ResponseEntity.ok(clienteService.crear(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){
        clienteService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
