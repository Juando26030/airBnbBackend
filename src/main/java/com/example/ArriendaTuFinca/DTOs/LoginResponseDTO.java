package com.example.ArriendaTuFinca.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
    private String correo;
    private String rol;
}
