package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ArriendaTuFinca.models.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Métodos personalizados si es necesario
}
