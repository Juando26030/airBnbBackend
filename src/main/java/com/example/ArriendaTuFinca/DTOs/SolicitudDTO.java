package com.example.ArriendaTuFinca.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class SolicitudDTO {
    private Long solicitud_id;
    private UsuarioDTO arrendatario_id;
    private PropiedadDTO propiedad_id;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private int precio_por_noche;
}
