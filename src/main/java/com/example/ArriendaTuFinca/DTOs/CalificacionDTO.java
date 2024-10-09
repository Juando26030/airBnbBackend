package com.example.ArriendaTuFinca.DTOs;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CalificacionDTO {
    private Long calificacion_id;
    private SolicitudDTO solicitud_id; // Toca dejarlo asi porque Hybernate lo cambia
    private int calificacion_propiedad;
    private int calificacion_arrendatario;
    private String comentario;
    private LocalDate fecha_calificacion;
}
