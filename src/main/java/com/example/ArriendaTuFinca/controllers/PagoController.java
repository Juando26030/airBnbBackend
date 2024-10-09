package com.example.ArriendaTuFinca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import com.example.ArriendaTuFinca.services.PagoService;
import com.example.ArriendaTuFinca.DTOs.PagoDTO;

@RestController
@RequestMapping( value = "/api/pagos")
public class PagoController {

    // Inyección de dependencias
    @Autowired
    private PagoService pagoService;

    // CRUD Endpoints

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PagoDTO> get() {
        return pagoService.get();
    }

    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagoDTO obtenerPagoPorId(@PathVariable Long id) {
        return pagoService.obtenerPagoPorId(id);
    }

    // Create
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagoDTO crearPago(@RequestBody PagoDTO pagoDTO) {
        return pagoService.crearPago(pagoDTO);
    }

    // Update
    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagoDTO actualizarPago(@RequestBody PagoDTO pagoDTO) {
        return pagoService.actualizarPago(pagoDTO);
    }

    // Delete
    @CrossOrigin
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarPago(@PathVariable PagoDTO pagoDTO) {
        pagoService.eliminarPago(pagoDTO);
    }

    // Delete by id
    @CrossOrigin
    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarPagoPorId(@PathVariable Long id) {
        pagoService.eliminarPagoPorId(id);
    }
}
