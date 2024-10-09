package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ArriendaTuFinca.models.Propiedad;
import java.util.List;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

    // Buscar propiedades por ubicación y cantidad de personas exactas
    List<Propiedad> findByDepartamentoAndCantPersonas(String departamento, int cantPersonas);

    // Buscar propiedades solo por ubicación
    List<Propiedad> findByDepartamento(String departamento);

    // Buscar propiedades solo por cantidad de personas
    List<Propiedad> findByCantPersonas(int cantPersonas);
}
