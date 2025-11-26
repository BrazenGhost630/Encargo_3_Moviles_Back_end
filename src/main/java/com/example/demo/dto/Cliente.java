package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "name", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacio")
    @Size(max = 100, message = "El correo debe tener como maximo 100 caracteres")
    @Email(message = "el correo no es valido")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Contraseña no puede estar vacia")
    @Size(min = 8, message = "Contraseña debe tener como minimo 8 caracteres")
    @Column(name = "password", nullable = false)
    private String contrasenia;

    

}
