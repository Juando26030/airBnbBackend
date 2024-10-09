package com.example.ArriendaTuFinca.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDTO {
    private Long departamentoId;
    private String nombre;
    private List<MunicipioDTO> municipios;
}
