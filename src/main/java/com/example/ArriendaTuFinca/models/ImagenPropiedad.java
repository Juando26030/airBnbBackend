package com.example.ArriendaTuFinca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ImagenPropiedad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagenPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imagenId;

    private String url; // URL de la imagen

    @ManyToOne
    @JoinColumn(name = "propiedad_id", referencedColumnName = "propiedadId", nullable = false)
    private Propiedad propiedad;
}
