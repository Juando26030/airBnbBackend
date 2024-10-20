package com.example.ArriendaTuFinca.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropiedadDTO {
    private Long propiedad_id;
    private UsuarioDTO arrendador_id; // Toca dejarlo asi porque Hybernate lo cambia
    private String imagen;
    private String nombre;
    private String departamento;
    private String municipio;
    private String tipo_de_ingreso;
    private String descripcion;
    private int cant_banos;
    private int cant_habitaciones;
    private int cantPersonas;
    private boolean mascotas;
    private boolean piscina;
    private boolean asador;
    private int valor_noche;
    private boolean visible;
    private int calificacion;
    private String estado;
}