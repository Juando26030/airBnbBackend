package com.example.ArriendaTuFinca.DTOs;

import com.example.ArriendaTuFinca.models.Imagen;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImagenDTO{
    private Long imagen_id;
    private String Url;

    private List<PropiedadDTO> propiedades;
}
