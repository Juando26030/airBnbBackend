package com.example.ArriendaTuFinca.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Municipio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long municipioId;

    @Column(nullable = false)
    private String nombre;

    // Relación Many-to-One con Departamento
    @ManyToOne
    @JoinColumn(name = "departamentoId", nullable = false)
    private Departamento departamento;
}
