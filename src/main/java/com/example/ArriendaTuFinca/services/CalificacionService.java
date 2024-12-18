package com.example.ArriendaTuFinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.repository.CalificacionRepository;
import com.example.ArriendaTuFinca.repository.SolicitudRepository;
import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.models.Calificacion;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;
import com.example.ArriendaTuFinca.DTOs.CalificacionDTO;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ModelMapper modelMapper;

    //se van a retornar DTOs

    //get
    public List<CalificacionDTO> get() {
        List<Calificacion> calificaciones = calificacionRepository.findAll();
        List<CalificacionDTO> calificacionesDTO = calificaciones.stream() 
                                       .map(calificacion -> modelMapper.map(calificacion, CalificacionDTO.class))
                                       .collect(Collectors.toList());   
        return calificacionesDTO;
    }

    //get por id
    public CalificacionDTO obtenerCalificacionPorId(Long id) {
        Optional<Calificacion> calificacion = calificacionRepository.findById(id); //Optional es un contenedor que puede o no contener un valor no nulo
        CalificacionDTO calificacionDTO = null;
        if (calificacion.isPresent()) {
            calificacionDTO = modelMapper.map(calificacion.get(), CalificacionDTO.class);
        }
        return calificacionDTO;
    }

    //post
    public CalificacionDTO crearCalificacion(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = modelMapper.map(calificacionDTO, Calificacion.class);

        SolicitudDTO solicitudDTO = calificacionDTO.getSolicitudId();
        long solicitud_id = solicitudDTO.getSolicitudId();

        Optional<Solicitud> solicitud = solicitudRepository.findById(solicitud_id);

        if(solicitud.isPresent()){
            calificacion.getSolicitud().setSolicitudId(solicitud.get().getSolicitudId());
            calificacion = calificacionRepository.save(calificacion);
            calificacionDTO.setCalificacionId(calificacion.getCalificacionId());
            return calificacionDTO;
        }
        
        throw new RuntimeException("No se encontro la solicitud con el id: " + solicitud_id);
    }

    //put
    public CalificacionDTO actualizarCalificacion(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = modelMapper.map(calificacionDTO, Calificacion.class);

        SolicitudDTO solicitudDTO = calificacionDTO.getSolicitudId();
        long solicitud_id = solicitudDTO.getSolicitudId();

        Optional<Solicitud> solicitud = solicitudRepository.findById(solicitud_id);

        if(solicitud.isPresent()){
            calificacion.getSolicitud().setSolicitudId(solicitud.get().getSolicitudId());
            calificacion = calificacionRepository.save(calificacion);
            calificacionDTO.setCalificacionId(calificacion.getCalificacionId());
            return calificacionDTO;
        }
        
        throw new RuntimeException("No se encontro la solicitud con el id: " + solicitud_id);
    }

    //delete
    public void eliminarCalificacion(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = modelMapper.map(calificacionDTO, Calificacion.class);
        calificacionRepository.delete(calificacion);
    }

    //delete por id
    public void eliminarCalificacionPorId(Long id) {
        calificacionRepository.deleteById(id);
    }
}
