package com.example.ArriendaTuFinca.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
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
        long arrendatarioId = solicitudDTO.getArrendatario().getUsuarioId();
        long propiedadId = solicitudDTO.getPropiedad().getPropiedadId();

        Usuario arrendatario = usuarioRepository.findById(arrendatarioId)
                .orElseThrow(() -> new EntityNotFoundException("Arrendatario no encontrado"));
        Propiedad propiedad = propiedadRepository.findById(propiedadId)
                .orElseThrow(() -> new EntityNotFoundException("Propiedad no encontrada"));

        // Mapea el DTO a la entidad
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitud.setArrendatario(arrendatario);
        solicitud.setPropiedad(propiedad);

        // Guarda la solicitud en la base de datos
        solicitud = solicitudRepository.save(solicitud);

        // Verifica el ID generado
        System.out.println("ID generado: " + solicitud.getSolicitudId());

        // Mapea la entidad actualizada al DTO y devuelve
        return modelMapper.map(solicitud, SolicitudDTO.class);
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

    public List<SolicitudDTO> getSolicitudesByArrendadorId(Long arrendadorId) {
        // Filtrar solicitudes por el ID del arrendador
        List<Solicitud> solicitudes = solicitudRepository.findByPropiedadArrendadorUsuarioId(arrendadorId);

        // Mapear entidades Solicitud a DTOs
        return solicitudes.stream()
                .map(solicitud -> modelMapper.map(solicitud, SolicitudDTO.class))
                .collect(Collectors.toList());
    }
    
}
