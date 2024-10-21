package com.example.ArriendaTuFinca.DTOs;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PagoDTO {
    private long pagoId;
    private SolicitudDTO solicitudId;
    private int monto;
    private LocalDate fechaPago;
}
