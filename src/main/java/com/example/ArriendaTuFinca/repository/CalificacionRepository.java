package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ArriendaTuFinca.models.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    // Métodos personalizados si es necesario
}