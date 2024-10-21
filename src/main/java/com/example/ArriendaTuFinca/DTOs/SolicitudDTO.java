package com.example.ArriendaTuFinca.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class SolicitudDTO {
    private Long solicitudId;
    private UsuarioDTO arrendatarioId;
    private PropiedadDTO propiedadId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int precioPorNoche;
}
