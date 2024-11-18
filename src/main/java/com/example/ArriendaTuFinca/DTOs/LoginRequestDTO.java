package com.example.ArriendaTuFinca.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String correo;
    private String contrasenia;
}