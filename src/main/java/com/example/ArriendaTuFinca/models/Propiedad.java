package com.example.ArriendaTuFinca.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import ch.qos.logback.core.status.Status;
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
import javax.persistence.Transient;

@Entity
@Table(name = "Propiedad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propiedad_id;

    @ManyToOne
    @JoinColumn(name = "arrendador_id", referencedColumnName = "usuario_id", unique = false, nullable = false)  //arrendador_id es el nombre de la columna en la tabla Propiedad
    private Usuario arrendador_id;

    private String imagen;
    private String nombre;
    private String departamento;
    private String municipio;
    private String tipo_de_ingreso;
    private String descripcion;
    private int cant_banos;
    private int cant_habitaciones;
    private int cantPersonas;   //y al otro lado que??????
    private boolean mascotas;
    private boolean piscina;
    private boolean asador;
    private int valor_noche;
    private boolean visible;
    private int calificacion;

    Estado estado;
}
