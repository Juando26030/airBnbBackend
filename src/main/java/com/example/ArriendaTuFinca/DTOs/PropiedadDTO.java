package com.example.ArriendaTuFinca.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropiedadDTO {
    private Long propiedadId;
    private UsuarioDTO arrendadorId; // Toca dejarlo asi porque Hybernate lo cambia
    private String imagen;
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
    private int valor_noche;
    private boolean visible;
    private int calificacion;
    private String estado;
}