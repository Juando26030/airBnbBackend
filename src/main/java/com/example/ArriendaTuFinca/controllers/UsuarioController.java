package com.example.ArriendaTuFinca.controllers;

import java.util.List;

import com.example.ArriendaTuFinca.DTOs.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        try {
            // Extraer los parámetros del cuerpo del JSON
            String correo = loginRequest.getCorreo();
            String contrasenia = loginRequest.getContrasenia();

            // Autenticar al usuario usando el servicio
            UsuarioDTO usuarioAutenticado = usuarioService.autenticarUsuario(correo, contrasenia);
            System.out.println("Id de usuario: "+usuarioAutenticado.getUsuarioId());

            // Si la autenticación es exitosa, devolvemos el objeto UsuarioDTO
            return ResponseEntity.ok(usuarioAutenticado);

        } catch (IllegalArgumentException e) {
            // Si ocurre un error, devolver una respuesta adecuada
            String errorMessage = e.getMessage();

            if (errorMessage.contains("autenticar su cuenta")) {
                // Usuario no autenticado porque no ha verificado su correo
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
            } else {
                // Credenciales incorrectas
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
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
            usuarioDTO.setUsuarioId(id);  // Asegura que el ID se use para actualizar el usuario correcto
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