package com.example.ArriendaTuFinca.DTOs;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PagoDTO {
    private long pago_id;
    private SolicitudDTO solicitud_id;
    private int monto;
    private LocalDate fecha_pago;
}
