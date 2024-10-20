package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ArriendaTuFinca.models.Propiedad;
import java.util.List;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

    // Buscar propiedades por departamento, municipio y cantidad de personas (todos los filtros)
    List<Propiedad> findByDepartamentoAndMunicipioAndCantPersonas(String departamento, String municipio, int cantPersonas);

    // Buscar propiedades por departamento y cantidad de personas (sin municipio)
    List<Propiedad> findByDepartamentoAndCantPersonas(String departamento, int cantPersonas);

    // Buscar propiedades por departamento y municipio (sin cantidad de personas)
    List<Propiedad> findByDepartamentoAndMunicipio(String departamento, String municipio);

    // Buscar propiedades solo por departamento
    List<Propiedad> findByDepartamento(String departamento);

    // Buscar propiedades solo por municipio
    List<Propiedad> findByMunicipio(String municipio);

    // Buscar propiedades solo por cantidad de personas
    List<Propiedad> findByCantPersonas(int cantPersonas);
}
