package com.example.ArriendaTuFinca.DTOs;

import com.example.ArriendaTuFinca.models.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long usuarioId;
    private String nombre;
    private String correo;
    private String telefono;
    private String contrasenia;
    private String confirmarContrasenia; // Campo de confirmación de contraseña
    private Rol rol; // Tipo de usuario
    private String estado;
    private Boolean autenticado; // Indica si el usuario está autenticado
}