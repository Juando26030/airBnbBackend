package com.example.ArriendaTuFinca.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.example.ArriendaTuFinca.repository.SolicitudRepository;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private ModelMapper modelMapper;

    //se van a retornar DTOs

    //get
    public List<SolicitudDTO> get() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        List<SolicitudDTO> solicitudesDTO = solicitudes.stream() 
                                       .map(solicitud -> modelMapper.map(solicitud, SolicitudDTO.class))
                                       .collect(Collectors.toList());   
        return solicitudesDTO;
    }

    //get por id
    public SolicitudDTO obtenerSolicitudPorId(Long id) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(id); //Optional es un contenedor que puede o no contener un valor no nulo
        SolicitudDTO solicitudDTO = null;
        if (solicitud.isPresent()) {
            solicitudDTO = modelMapper.map(solicitud.get(), SolicitudDTO.class);
        }
        return solicitudDTO;
    }

    public SolicitudDTO crearSolicitud(SolicitudDTO solicitudDTO) {
        UsuarioDTO arrendatarioDTO = solicitudDTO.getArrendatario();
        long arrendatarioId = arrendatarioDTO.getUsuarioId();

        PropiedadDTO propiedadDTO = solicitudDTO.getPropiedad();
        long propiedadId = propiedadDTO.getPropiedadId();

        Optional<Usuario> arrendatario = usuarioRepository.findById(arrendatarioId);
        Optional<Propiedad> propiedad = propiedadRepository.findById(propiedadId);

        // Configurar un convertidor para fechas en formato ISO
        ModelMapper modelMapper = new ModelMapper();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Convertidor de String a Date
        Converter<String, Date> toDate = ctx -> {
            try {
                return ctx.getSource() == null ? null : isoFormat.parse(ctx.getSource());
            } catch (ParseException e) {
                throw new RuntimeException("Error al parsear la fecha: " + ctx.getSource(), e);
            }
        };

        // Convertidor de Date a String
        Converter<Date, String> toString = ctx -> ctx.getSource() == null ? null : isoFormat.format(ctx.getSource());

        // Agregar convertidores al modelMapper
        modelMapper.typeMap(SolicitudDTO.class, Solicitud.class)
                .addMappings(mapper -> {
                    mapper.using(toDate).map(SolicitudDTO::getFechaInicio, Solicitud::setFechaInicio);
                    mapper.using(toDate).map(SolicitudDTO::getFechaFin, Solicitud::setFechaFin);
                });

        modelMapper.typeMap(Solicitud.class, SolicitudDTO.class)
                .addMappings(mapper -> {
                    mapper.using(toString).map(Solicitud::getFechaInicio, SolicitudDTO::setFechaInicio);
                    mapper.using(toString).map(Solicitud::getFechaFin, SolicitudDTO::setFechaFin);
                });

        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);

        if (arrendatario.isPresent() && propiedad.isPresent()) {
            solicitud.setArrendatario(arrendatario.get());
            solicitud.setPropiedad(propiedad.get());

            solicitudRepository.save(solicitud);
            return modelMapper.map(solicitud, SolicitudDTO.class);
        }

        throw new RuntimeException("No se pudo crear la solicitud");
    }

    //put
    public SolicitudDTO actualizarSolicitud(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitud = solicitudRepository.save(solicitud);
        solicitudDTO = modelMapper.map(solicitud, SolicitudDTO.class);
        return solicitudDTO;
    }

    //delete
    public void eliminarSolicitud(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitudRepository.delete(solicitud);
    }

    //delete por id
    public void eliminarSolicitudPorId(Long id) {
        solicitudRepository.deleteById(id);
    }
    
}
