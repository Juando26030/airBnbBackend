package com.example.ArriendaTuFinca.DTOs;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CalificacionDTO {
    private Long calificacionId;
    private SolicitudDTO solicitudId; // Toca dejarlo asi porque Hybernate lo cambia
    private int calificacionPropiedad;
    private int calificacionArrendatario;
    private String comentario;
    private LocalDate fechaCalificacion;
}
