package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ArriendaTuFinca.models.Solicitud;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByPropiedadArrendadorUsuarioId(Long arrendadorId);
    //metodos de consulta{
}
