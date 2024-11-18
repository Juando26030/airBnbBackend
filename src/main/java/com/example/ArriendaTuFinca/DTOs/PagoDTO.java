package com.example.ArriendaTuFinca.DTOs;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class PagoDTO {
    private long pagoId;
    private SolicitudDTO solicitud;
    private int monto;
    private Date fechaPago;

    private long cardNumber;
    private int cvv;
    private Date expDate;
}
