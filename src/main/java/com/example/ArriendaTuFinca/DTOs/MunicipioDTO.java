package com.example.ArriendaTuFinca.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MunicipioDTO {
    private Long municipioId;
    private String nombre;
    private Long departamentoId; // Aquí solo el ID del Departamento, no el objeto completo
}
