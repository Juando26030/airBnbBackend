package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.DTOs.LoginRequestDTO;
import com.example.ArriendaTuFinca.services.AuthenticationService;
import com.example.ArriendaTuFinca.services.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/autenticacion")
public class AuthenticationController {

    @Autowired
    private AuthenticationService autenticacionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        System.out.println("Contraseña sin encriptar: " + body.getContrasenia());
        System.out.println("Entra a endpoint login");
        System.out.println(body.getCorreo());
        // No encriptes aquí
        return ResponseEntity.ok(
                autenticacionService.login(body.getCorreo(), body.getContrasenia())
        );
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String token) {
        System.out.println("refresh" + token);
        return ResponseEntity.ok(
                autenticacionService.refresh(token)
        );
    }

}

