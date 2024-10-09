package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ArriendaTuFinca.models.Usuario;

// This will be AUTO IMPLEMENTED by Spring into a Bean called contactoRepository
// que pvtas es un Bean?    
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //metodos de consulta
}
