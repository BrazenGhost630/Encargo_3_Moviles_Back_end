package com.example.demo.service;

import java.util.List;


import org.springframework.stereotype.Service;
import com.example.demo.dto.Cliente;
import com.example.demo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

// Logica del programa

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    // Función que busca y devuelve todos.
    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }


    
    public Cliente crear(Cliente c){
        return clienteRepository.save(c);
    }

    // Funcion que borra.
    public void borrar(Long id){
        clienteRepository.deleteById(id);
    }
}
