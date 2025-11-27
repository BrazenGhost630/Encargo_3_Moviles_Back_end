package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.Cliente;
import com.example.demo.service.ClienteService;
import lombok.RequiredArgsConstructor;


// Manejo de endpoints.

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@CrossOrigin("*") // Autoriza comunicaciones no seguras.
public class ClienteController {
    
    private final ClienteService clienteService;


    private boolean isValid(Map<String, String> credenciales) {
        return credenciales != null &&
               credenciales.containsKey("email") && credenciales.containsKey("password");
            }

    @GetMapping
    public List<Cliente> buscarTodos(){
        return clienteService.buscarTodos();
    }

    @PostMapping("/login")
    
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {

        String email = credenciales.get("email");
        String password = credenciales.get("password");

       
        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Debe proporcionar email y password.");
        }

        boolean isAuthenticated = clienteService.Login(email, password); 

        if (isAuthenticated) {
           
            return ResponseEntity.ok("Login exitoso.");
        } else {
           
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        }
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> credenciales) {

        if (!isValid(credenciales)) {
            return ResponseEntity.badRequest().body("Faltan credenciales (email o password).");
        }

        String email = credenciales.get("email");
        String password = credenciales.get("password");

        
        Cliente nuevoUsuario = clienteService.register(email, password);

        if (nuevoUsuario != null) {
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso.");
        } else {
            
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario con ese email ya está registrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){
        clienteService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
