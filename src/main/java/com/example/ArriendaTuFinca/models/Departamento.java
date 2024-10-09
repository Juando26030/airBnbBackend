package com.example.ArriendaTuFinca.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Departamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departamentoId;

    @Column(nullable = false)
    private String nombre;

    // Relación One-to-Many con Municipio
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Municipio> municipios;
}
