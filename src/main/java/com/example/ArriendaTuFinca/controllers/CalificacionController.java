package com.example.ArriendaTuFinca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import com.example.ArriendaTuFinca.services.CalificacionService;
import com.example.ArriendaTuFinca.DTOs.CalificacionDTO;


@RestController
@RequestMapping( value = "/api/calificaciones")
public class CalificacionController {
    
    // Inyección de dependencias
    @Autowired
    private CalificacionService calificacionService;

    // CRUD Endpoints

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CalificacionDTO> get() {
        return calificacionService.get();
    }

    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalificacionDTO obtenerCalificacionPorId(@PathVariable Long id) {
        return calificacionService.obtenerCalificacionPorId(id);
    }

    // Create
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CalificacionDTO crearCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        return calificacionService.crearCalificacion(calificacionDTO);
    }

    // Update
    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CalificacionDTO actualizarCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        return calificacionService.actualizarCalificacion(calificacionDTO);
    }

    // Delete
    @CrossOrigin
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarCalificacion(@PathVariable CalificacionDTO calificacionDTO) {
        calificacionService.eliminarCalificacion(calificacionDTO);
    }

    // Delete by id
    @CrossOrigin
    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarCalificacionPorId(@PathVariable Long id) {
        calificacionService.eliminarCalificacionPorId(id);
    }
}
