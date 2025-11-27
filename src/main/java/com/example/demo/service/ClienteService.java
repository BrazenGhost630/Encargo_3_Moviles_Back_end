package com.example.demo.service;



import java.util.List;
import java.util.Optional;

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

    public boolean Login(String email, String password) {

     
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);

        
        if (clienteOpt.isEmpty()) {
            return false;
        }

        Cliente cliente = clienteOpt.get();

       
        return password.equals(cliente.getContrasenia());
    }


    
    public Cliente register(String email, String password) {
        // 1. Verificar si el usuario ya existe
        if (clienteRepository.findByEmail(email).isPresent()) {
            // Devuelve null o lanza una excepción para indicar que ya existe.
            // Para simplicidad, devolveremos null.
            return null;
        }

        // 2. Crear y guardar el nuevo usuario
        Cliente nuevoUsuario = new Cliente();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setContrasenia(password); // Almacenado sin encriptar

        return clienteRepository.save(nuevoUsuario);
    }

    // Funcion que borra.
    public void borrar(Long id){
        clienteRepository.deleteById(id);
    }
}
