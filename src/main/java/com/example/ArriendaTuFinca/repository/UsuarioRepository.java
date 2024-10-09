package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;


import com.example.ArriendaTuFinca.models.Usuario;

// This will be AUTO IMPLEMENTED by Spring into a Bean called contactoRepository
// que pvtas es un Bean?

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para autenticar un usuario por correo y contraseña
    Optional<Usuario> findByCorreoAndContrasenia(String correo, String contrasenia);
    // Método para encontrar usuario por correo
    Optional<Usuario> findByCorreo(String correo); // Método para encontrar usuario por correo
}