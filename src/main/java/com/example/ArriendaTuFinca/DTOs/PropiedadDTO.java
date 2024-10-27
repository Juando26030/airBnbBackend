package com.example.ArriendaTuFinca.DTOs;

import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PropiedadDTO {
    private Long propiedadId;
    private Long arrendadorId;
    private String nombre;
    private String departamento;
    private String municipio;
    private String tipoDeIngreso;
    private String descripcion;
    private int cantBanos;
    private int cantHabitaciones;
    private int cantPersonas;
    private boolean mascotas;
    private boolean piscina;
    private boolean asador;
    private int valorNoche;
    private boolean visible;
    private float calificacion;
    private String estado;

    private List<String> imagenes; // Lista de URLs de imágenes
}
