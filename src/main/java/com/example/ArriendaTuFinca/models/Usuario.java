package com.example.ArriendaTuFinca.models;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_id;

    private String nombre;
    private String correo;
    private String telefono;
    private String contrasenia;
    Estado estado;

    //@Enumerated(EnumType.STRING)
    private String rol;

    /*
    //esto la verdad no se pa que
    @OneToMany(mappedBy = "arrendador_id")
    private List<Propiedad> propiedades = new ArrayList<>();

    @OneToMany(mappedBy = "arrendatario_id")
    private List<Solicitud> solicitudes = new ArrayList<>();

    */
}
