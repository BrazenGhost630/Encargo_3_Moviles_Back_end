package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional <Cliente> findByEmail(String email);
    
}
