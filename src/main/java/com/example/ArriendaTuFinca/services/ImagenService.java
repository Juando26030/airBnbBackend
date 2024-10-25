package com.example.ArriendaTuFinca.services;

import com.example.ArriendaTuFinca.DTOs.ImagenDTO;
import com.example.ArriendaTuFinca.models.Imagen;
import com.example.ArriendaTuFinca.repository.ImagenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ImagenDTO crearImagen(ImagenDTO imagenDTO) {
        Imagen imagen = modelMapper.map(imagenDTO, Imagen.class);
        imagen = imagenRepository.save(imagen);
        return modelMapper.map(imagen, ImagenDTO.class);
    }

    public ImagenDTO actualizarImagen(Long id, ImagenDTO imagenDTO) {
        Optional<Imagen> imagenOptional = imagenRepository.findById(id);
        if (imagenOptional.isPresent()) {
            Imagen imagen = imagenOptional.get();
            imagen.setUrl(imagenDTO.getUrl());
            imagen = imagenRepository.save(imagen);
            return modelMapper.map(imagen, ImagenDTO.class);
        } else {
            throw new IllegalArgumentException("La imagen con ID " + id + " no existe.");
        }
    }

    public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }

    public ImagenDTO obtenerImagenPorId(Long id) {
        Optional<Imagen> imagenOptional = imagenRepository.findById(id);
        if (imagenOptional.isPresent()) {
            return modelMapper.map(imagenOptional.get(), ImagenDTO.class);
        } else {
            throw new IllegalArgumentException("La imagen con ID " + id + " no existe.");
        }
    }

    public List<ImagenDTO> obtenerTodasLasImagenes() {
        return imagenRepository.findAll().stream()
                .map(imagen -> modelMapper.map(imagen, ImagenDTO.class))
                .collect(Collectors.toList());
    }
}
