package com.example.ArriendaTuFinca.models;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Propiedad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propiedadId;

    @ManyToOne
    @JoinColumn(name = "arrendador_id", referencedColumnName = "usuarioId", nullable = false)
    private Usuario arrendador;

    private String nombre;
    private String departamento;
    private String municipio;
    private String tipoDeIngreso;
    private String descripcion;
    private int cantBanos;
    private int cantHabitaciones;
    private int cantPersonas;
    private boolean mascotas;
    private boolean piscina;
    private boolean asador;
    private int valorNoche;
    private boolean visible;
    private float promedioCalificacion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    // Relación con ImagenPropiedad
    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenPropiedad> imagenes = new ArrayList<>();

    public enum Estado {
        ACTIVO, INACTIVO
    }
}

