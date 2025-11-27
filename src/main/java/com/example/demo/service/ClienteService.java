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

    public boolean authenticate(String email, String password) {
    Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);

    if (clienteOpt.isPresent()) {
        Cliente cliente = clienteOpt.get();
        // 💡 SIN ENCRIPTACIÓN: Comparamos las contraseñas en texto plano.
        if (password.equals(cliente.getContrasenia())) {
            return true; // Éxito: Solo devolvemos 'true'
        }
    }
    return false; // Fallo
}

    public boolean Login(String email, String password) {

     
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);

        
        if (clienteOpt.isEmpty()) {
            return false;
        }

        Cliente cliente = clienteOpt.get();

       
        return password.equals(cliente.getContrasenia());
    }


    
    public Cliente register(String email, String password, String nombre) {
    // 1. Verificar si el usuario ya existe (lógica previa)
    if (clienteRepository.findByEmail(email).isPresent()) {
        // Devuelve null o lanza excepción si ya existe
        return null;
    }

    // 2. Crear y guardar el nuevo usuario
    Cliente nuevoUsuario = new Cliente();
    
    // Asignamos el nombre recibido de la petición (del controlador)
    // ✅ FIX PRINCIPAL
    nuevoUsuario.setNombre(nombre); 
    
    nuevoUsuario.setEmail(email);
    nuevoUsuario.setContrasenia(password); // Asumo que usas setContrasenia por tu entidad Cliente

    return clienteRepository.save(nuevoUsuario);
}

    
    public void borrar(Long id){
        clienteRepository.deleteById(id);
    }
}
