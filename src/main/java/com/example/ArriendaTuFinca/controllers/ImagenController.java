package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.DTOs.ImagenDTO;
import com.example.ArriendaTuFinca.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @PostMapping
    public ImagenDTO crearImagen(@RequestBody ImagenDTO imagenDTO) {
        return imagenService.crearImagen(imagenDTO);
    }

    @PutMapping("/{id}")
    public ImagenDTO actualizarImagen(@PathVariable Long id, @RequestBody ImagenDTO imagenDTO) {
        return imagenService.actualizarImagen(id, imagenDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarImagen(@PathVariable Long id) {
        imagenService.eliminarImagen(id);
    }

    @GetMapping("/{id}")
    public ImagenDTO obtenerImagenPorId(@PathVariable Long id) {
        return imagenService.obtenerImagenPorId(id);
    }

    @GetMapping
    public List<ImagenDTO> obtenerTodasLasImagenes() {
        return imagenService.obtenerTodasLasImagenes();
    }
}
