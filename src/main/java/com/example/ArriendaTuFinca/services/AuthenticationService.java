package com.example.ArriendaTuFinca.services;

import java.util.Optional;

import com.example.ArriendaTuFinca.DTOs.LoginResponseDTO;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public LoginResponseDTO login(String correo, String contrasenia) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);

        if (usuario.isEmpty()) {
            System.out.println("Usuario no encontrado en servicio auth back");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(contrasenia, usuario.get().getContrasenia())) {
            System.out.println("Contraseña incorrecta en servicio auth back");
            throw new BadCredentialsException("Credenciales inválidas");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(correo, contrasenia)
        );
        System.out.println("Usuario encontrado en servicio auth back");
        String token = jwtService.generateToken(usuario.get());
        return new LoginResponseDTO(
                token,
                correo,
                usuario.get().getRol().getTipoRol(),
                usuario.get().getUsuarioId() // Incluimos el ID del usuario
        );
    }



    public LoginResponseDTO refresh(String token) {
        token = token.substring(7);
        String username = jwtService.extractUserName(token);
        System.out.println("Username en refresh: " + username);
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        token = jwtService.generateToken(usuario.get());

        return new LoginResponseDTO(token, username, usuario.get().getRol().getTipoRol(), usuario.get().getUsuarioId());
    }

}

