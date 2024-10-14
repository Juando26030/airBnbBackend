package com.example.ArriendaTuFinca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.services.UsuarioService;


@RestController
@RequestMapping( value = "/api/usuarios")
public class UsuarioController {

    // Inyección de dependencias
    @Autowired
    private UsuarioService usuarioService;

    // CRUD Endpoints
    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioDTO> get() {
        return usuarioService.get();
    }

    // Endpoint para manejar el login
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String contrasenia) {
        try {
            UsuarioDTO usuarioAutenticado = usuarioService.autenticarUsuario(correo, contrasenia);
            if (usuarioAutenticado != null) {
                return ResponseEntity.ok(usuarioAutenticado);  // Usuario autenticado con éxito
            } else {
                return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
            }
        } catch (IllegalArgumentException e) {
            // Si el mensaje es sobre la autenticación, devolvemos 403 (Forbidden)
            if (e.getMessage().contains("autenticar su cuenta")) {
                return ResponseEntity.status(403).body(e.getMessage()); // Usuario no autenticado
            } else {
                return ResponseEntity.status(401).body(e.getMessage()); // Credenciales incorrectas
            }
        }
    }


    // Validar
    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Create
    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
            return ResponseEntity.ok(nuevoUsuario);  // Devuelve el nuevo usuario creado
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());  // Devuelve el error si el correo ya existe o algún problema ocurre
        }
    }

    @CrossOrigin
    @GetMapping("/activar/{id}")
    public ResponseEntity<?> activarUsuario(@PathVariable Long id) {
        try {
            UsuarioDTO usuario = usuarioService.activarUsuario(id);
            return ResponseEntity.ok("Usuario activado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }



    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioDTO.setUsuario_id(id);  // Asegura que el ID se use para actualizar el usuario correcto
            UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(usuarioDTO);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    // Delete
    @CrossOrigin
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.eliminarUsuario(usuarioDTO);
    }

    // Delete by id
    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuarioPorId(id);
    }
}