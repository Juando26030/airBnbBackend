package com.example.ArriendaTuFinca.services;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.repository.PagoRepository;
import com.example.ArriendaTuFinca.repository.SolicitudRepository;
import com.example.ArriendaTuFinca.models.Pago;
import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;
import com.example.ArriendaTuFinca.DTOs.PagoDTO;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ModelMapper modelMapper;

    //se van a retornar DTOs

    //get
    public List<PagoDTO> get() {
        List<Pago> pagos = pagoRepository.findAll();
        List<PagoDTO> pagosDTO = pagos.stream() 
                                       .map(pago -> modelMapper.map(pago, PagoDTO.class))
                                       .collect(Collectors.toList());   
        return pagosDTO;
    }

    //get por id
    public PagoDTO obtenerPagoPorId(Long id) {
        Optional<Pago> pago = pagoRepository.findById(id); //Optional es un contenedor que puede o no contener un valor no nulo
        PagoDTO pagoDTO = null;
        if (pago.isPresent()) {
            pagoDTO = modelMapper.map(pago.get(), PagoDTO.class);
        }
        return pagoDTO;
    }

    //post
    public PagoDTO crearPago(PagoDTO pagoDTO) {
        Pago pago = modelMapper.map(pagoDTO, Pago.class);

        SolicitudDTO solicitudDTO = pagoDTO.getSolicitud_id(); //Obtengo el objeto con id
        long solicitud_id = solicitudDTO.getSolicitud_id();    //Obtengo el id de la solicitud

        Optional<Solicitud> solicitud = solicitudRepository.findById(solicitud_id); //Busco en bdd

        if (solicitud.isPresent()) {
            pago.setSolicitud_id(solicitud.get());  //guardo el objeto
            pago = pagoRepository.save(pago);       //guardo el pago
            pagoDTO.setPago_id(pago.getPago_id());  //retorno el DTO
            return pagoDTO;
        }
        
        throw new RuntimeException("No se encontro la solicitud con id: " + solicitud_id);
    }

    //put
    public PagoDTO actualizarPago(PagoDTO pagoDTO) {
        Pago pago = modelMapper.map(pagoDTO, Pago.class);

        SolicitudDTO solicitudDTO = pagoDTO.getSolicitud_id();
        long solicitud_id = solicitudDTO.getSolicitud_id();

        Optional<Solicitud> solicitud = solicitudRepository.findById(solicitud_id);

        if (solicitud.isPresent()) {
            pago.setSolicitud_id(solicitud.get());
            pago = pagoRepository.save(pago);
            pagoDTO.setPago_id(pago.getPago_id());
            return pagoDTO;
        }
        
        throw new RuntimeException("No se encontro la solicitud con id: " + solicitud_id);
    }

    //delete
    public void eliminarPago(PagoDTO pagoDTO) {
        Pago pago = modelMapper.map(pagoDTO, Pago.class);
        pagoRepository.delete(pago);
    }

    //delete por id
    public void eliminarPagoPorId(Long id) {
        pagoRepository.deleteById(id);
    }

}
