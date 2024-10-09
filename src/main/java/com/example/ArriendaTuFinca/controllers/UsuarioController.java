package com.example.ArriendaTuFinca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.services.UsuarioService;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;


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

    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Create
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crearUsuario(usuarioDTO);
    }

    // UpdateCLARO NO FUNCIONA PORQUE NO HACE NADA CON EL ID QUE LLEGA 
    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.actualizarUsuario(usuarioDTO);
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
