package com.example.ArriendaTuFinca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.services.PropiedadService;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;



@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    
    // CRUD Endpoints

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO> get() {
        return propiedadService.get();
    }

    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO obtenerPropiedadPorId(@PathVariable Long id) {
        return propiedadService.obtenerPropiedadPorId(id);
    }

    // Create
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO crearPropiedad(@RequestBody PropiedadDTO propiedadDTO) {
        return propiedadService.crearPropiedad(propiedadDTO);
    }

    // Update
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO actualizarPropiedad(@PathVariable Long id, @RequestBody PropiedadDTO propiedadDTO) {
        return propiedadService.actualizarPropiedad(id, propiedadDTO);
    }

    // Delete
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarPropiedad(@PathVariable Long id) {
        propiedadService.eliminarPropiedad(id);
    }


    // Método para buscar propiedades por filtros de ubicación y/o cantidad de personas
    @CrossOrigin
    @GetMapping(value = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO> buscarPropiedades(@RequestParam(value = "ubicacion", required = false) String ubicacion,
                                                @RequestParam(value = "cant_personas", required = false) Integer cantPersonas) {
        return propiedadService.buscarPropiedadesPorFiltros(ubicacion, cantPersonas);
    }
    
}