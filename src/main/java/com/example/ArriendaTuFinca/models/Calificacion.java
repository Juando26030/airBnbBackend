package com.example.ArriendaTuFinca.models;

//import java.util.Arrays;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.List;
//import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;


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
@Table(name = "Calificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calificacionId;

    //mirar si hay problema en que sean iguales
    @ManyToOne
    @JoinColumn(name = "solicitud_id", referencedColumnName = "solicitudId", unique = false, nullable = false)
    private Solicitud solicitud;
    private int calificacionPropiedad;
    private int calificacionArrendatario;
    private String comentario;
    private Date fechaCalificacion;
}
