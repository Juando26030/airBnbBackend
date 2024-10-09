package com.example.ArriendaTuFinca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.services.SolicitudService;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;


@RestController
@RequestMapping( value = "/api/solicitudes")
public class SolicitudController {

    // Inyección de dependencias
    @Autowired
    private SolicitudService solicitudService;

    // CRUD Endpoints

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolicitudDTO> get() {
        return solicitudService.get();
    }

    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO obtenerSolicitudPorId(@PathVariable Long id) {
        return solicitudService.obtenerSolicitudPorId(id);
    }

    // Create
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO crearSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        return solicitudService.crearSolicitud(solicitudDTO);
    }

    // Update   CLARO NO FUNCIONA PORQUE NO HACE NADA CON EL ID QUE LLEGA 
    /* 
    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO actualizarSolicitud(@PathVariable Long id, @RequestBody SolicitudDTO solicitudDTO) {
        return solicitudService.actualizarSolicitud(solicitudDTO);
    }*/

    // Delete
    @CrossOrigin
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarSolicitud(@PathVariable SolicitudDTO solicitudDTO) {
        solicitudService.eliminarSolicitud(solicitudDTO);
    }

    // Delete by id
    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarSolicitud(@PathVariable Long id) {
        solicitudService.eliminarSolicitudPorId(id);
    }

    
    
}
