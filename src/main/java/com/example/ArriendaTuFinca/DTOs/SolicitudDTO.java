package com.example.ArriendaTuFinca.DTOs;

import com.example.ArriendaTuFinca.models.Usuario;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class SolicitudDTO {
    private Long solicitudId;
    private UsuarioDTO arrendatario;
    private PropiedadDTO propiedad;
    private int huespedes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date fechaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date fechaFin;
}
