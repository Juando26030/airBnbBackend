package com.example.ArriendaTuFinca.models;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import ch.qos.logback.core.joran.action.TimestampAction;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Solicitud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solicitud_id;

    @ManyToOne
    @JoinColumn(name = "arrendatario_id", referencedColumnName = "usuario_id", unique = false, nullable = false) 
    private Usuario arrendatario_id;

    @ManyToOne //raro
    @JoinColumn(name = "propiedad_id", referencedColumnName = "propiedad_id", unique = false, nullable = false) 
    private Propiedad propiedad_id;

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private int precio_por_noche;
    
}
