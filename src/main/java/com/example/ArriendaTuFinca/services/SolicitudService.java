package com.example.ArriendaTuFinca.services;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.example.ArriendaTuFinca.repository.SolicitudRepository;
import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.models.Estado;
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

    //post
    //ese problema que
    public SolicitudDTO crearSolicitud(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        
        UsuarioDTO arrendatarioDTO = solicitudDTO.getArrendatario_id();
        long arrendatario_id = arrendatarioDTO.getUsuario_id();

        PropiedadDTO propiedadDTO = solicitudDTO.getPropiedad_id();
        long propiedad_id = propiedadDTO.getPropiedadId();

        Optional<Usuario> arrendatarioOptional = usuarioRepository.findById(arrendatario_id);
        Optional<Propiedad> propiedadOptional = propiedadRepository.findById(propiedad_id);

        if (arrendatarioOptional.isPresent() && propiedadOptional.isPresent()) {
            solicitud.setArrendatario_id(arrendatarioOptional.get());
            solicitud.setPropiedad_id(propiedadOptional.get());
            solicitud = solicitudRepository.save(solicitud);
            solicitudDTO.setSolicitud_id(solicitud.getSolicitud_id());
            return solicitudDTO;
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
