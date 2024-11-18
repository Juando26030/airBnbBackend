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
        System.out.println("Entra a servicio login");
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        System.out.println("Usuario encontrado");
        System.out.println(usuario.get().getCorreo());
        System.out.println(usuario.get().getContrasenia());

        // Validar la contraseña
        if (!passwordEncoder.matches(contrasenia, usuario.get().getContrasenia())) {
            System.out.println("Contraseña incorrecta");
            throw new BadCredentialsException("Credenciales inválidas");
        }
        System.out.println("Contraseña correcta");
        // Si la validación pasa, autentica con el AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(correo, contrasenia)
        );

        String token = jwtService.generateToken(usuario.get());
        return new LoginResponseDTO(token, correo, usuario.get().getRol().getTipoRol());
    }


    public LoginResponseDTO refresh(String token) {
        token = token.substring(7);
        String username = jwtService.extractUserName(token);
        Optional<Usuario> usuario = usuarioRepository.findByNombre(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        token = jwtService.generateToken(usuario.get());

        return new LoginResponseDTO(token, username, usuario.get().getRol().getTipoRol());
    }

}

