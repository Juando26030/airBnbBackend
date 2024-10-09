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

import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;


@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    //se van a retornar DTOs

    //get
    public List<PropiedadDTO> get() {
        List<Propiedad> propiedades = propiedadRepository.findAll();
        List<PropiedadDTO> propiedadesDTO = propiedades.stream() 
                                       .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                                       .collect(Collectors.toList());   
        return propiedadesDTO;
    }

    //get por id
    public PropiedadDTO obtenerPropiedadPorId(Long id) {
        Optional<Propiedad> propiedad = propiedadRepository.findById(id); //Optional es un contenedor que puede o no contener un valor no nulo
        PropiedadDTO propiedadDTO = null;
        if (propiedad.isPresent()) {
            propiedadDTO = modelMapper.map(propiedad.get(), PropiedadDTO.class);
        }
        return propiedadDTO;
    }

    //post
    //Aqui esta el error????  <---------------------

    /* 
    public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setEstado(Estado.ACTIVE);
        
        UsuarioDTO arrendador = propiedadDTO.getArrendador();
        Long arrendadorId = arrendador.getUsuario_id();
        Optional<Usuario> usuariOptional = usuarioRepository.findById(arrendadorId);
        if (usuariOptional.isPresent()) {
            propiedad.setArrendador(usuariOptional.get());
            propiedad = propiedadRepository.save(propiedad);
            propiedadDTO.setPropiedad_id(propiedad.getPropiedad_id());
            return propiedadDTO;
        }
        return null;   
    }    
        */

        public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO) {
            // Mapea el DTO a la entidad (model Mapper)
            Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
            propiedad.setEstado(Estado.ACTIVE);
        
            // Obtener el objeto UsuarioDTO del DTO de Propiedad
            UsuarioDTO arrendadorDTO = propiedadDTO.getArrendadorId();
            Long arrendadorId = arrendadorDTO.getUsuario_id();
            
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(arrendadorId); //del repo de Usu
            if (usuarioOptional.isPresent()) {
                // Usar el usuario existente en lugar de crear uno nuevo
                propiedad.setArrendadorId(usuarioOptional.get());    //trae todo el usu
                propiedad = propiedadRepository.save(propiedad);   //guarda la propiedad (persistencia)
        
                // Actualizar el DTO con el ID generado de la propiedad
                propiedadDTO.setPropiedadId(propiedad.getPropiedad_id());
                return propiedadDTO;
            }
        
            // Manejar el caso en que el usuario no exista
            throw new IllegalArgumentException("El arrendador con ID " + arrendadorId + " no existe.");
        }
        
    //put
    public PropiedadDTO actualizarPropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setEstado(Estado.ACTIVE);
        propiedad.setPropiedad_id(id);
        propiedadDTO = modelMapper.map(propiedadRepository.save(propiedad), PropiedadDTO.class);
        return propiedadDTO;
    }

    //delete por id
    public void eliminarPropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }


    // Método que implementa la lógica de búsqueda por ubicación y/o cantidad de personas
    public List<PropiedadDTO> buscarPropiedadesPorFiltros(String ubicacion, Integer cantPersonas) {
        List<Propiedad> propiedadesFiltradas;

        // Si ambos parámetros están presentes
        if (ubicacion != null && cantPersonas != null) {
            propiedadesFiltradas = propiedadRepository.findByDepartamentoAndCantPersonas(ubicacion, cantPersonas);
        }
        // Si solo la ubicación está presente
        else if (ubicacion != null) {
            propiedadesFiltradas = propiedadRepository.findByDepartamento(ubicacion);
        }
        // Si solo la cantidad de personas está presente
        else if (cantPersonas != null) {
            propiedadesFiltradas = propiedadRepository.findByCantPersonas(cantPersonas);
        }
        // Si no se proporcionó ningún filtro, devuelve una lista vacía o todas las propiedades según prefieras
        else {
            propiedadesFiltradas = propiedadRepository.findAll();
        }

        // Convertir las entidades Propiedad a PropiedadDTO usando ModelMapper
        return propiedadesFiltradas.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());
    }
}

