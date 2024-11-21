package com.example.ArriendaTuFinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import com.example.ArriendaTuFinca.models.ImagenPropiedad;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;

import static com.example.ArriendaTuFinca.models.Propiedad.Estado.ACTIVO;


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
        Optional<Propiedad> propiedad = propiedadRepository.findById(id);
        if (propiedad.isPresent()) {
            // Mapeo de la entidad a DTO
            PropiedadDTO propiedadDTO = modelMapper.map(propiedad.get(), PropiedadDTO.class);

            // Mapear URLs de imágenes manualmente
            List<String> imagenUrls = propiedad.get().getImagenes().stream()
                    .map(ImagenPropiedad::getUrl)
                    .collect(Collectors.toList());
            propiedadDTO.setImagenes(imagenUrls);

            return propiedadDTO;
        }
        return null;
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
        // Mapear el DTO a la entidad Propiedad
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);

        // Establecer el arrendador si existe
        Optional<Usuario> arrendador = usuarioRepository.findById(propiedadDTO.getArrendadorId());
        arrendador.ifPresent(propiedad::setArrendador);

        // Procesar las URLs de imágenes y asignarlas a la propiedad
        if (propiedadDTO.getImagenes() != null) {
            Propiedad finalPropiedad = propiedad;
            List<ImagenPropiedad> imagenes = propiedadDTO.getImagenes().stream()
                    .map(url -> new ImagenPropiedad(null, url, finalPropiedad)) // Crear la entidad ImagenPropiedad
                    .collect(Collectors.toList());
            propiedad.setImagenes(imagenes); // Asignar las imágenes a la propiedad
        }

        // Guardar la propiedad con sus imágenes en la base de datos
        propiedad = propiedadRepository.save(propiedad);

        // Mapear la entidad guardada de vuelta al DTO y retornarlo
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }


    //put
    public PropiedadDTO actualizarPropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setEstado(ACTIVO);
        propiedad.setPropiedadId(id);
        propiedadDTO = modelMapper.map(propiedadRepository.save(propiedad), PropiedadDTO.class);
        return propiedadDTO;
    }

    //delete por id
    public void eliminarPropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }


    // Método para buscar propiedades por el id del arrendador (admin) y mapear URLs de imágenes
    public List<PropiedadDTO> buscarPropiedadesAdmin(Long id_admin) {
        // Obtener propiedades asociadas al arrendador (admin)
        List<Propiedad> propiedades = propiedadRepository.findByArrendador_UsuarioId(id_admin);

        // Convertir entidades a DTOs y mapear URLs de imágenes
        return propiedades.stream()
                .map(propiedad -> {
                    PropiedadDTO propiedadDTO = modelMapper.map(propiedad, PropiedadDTO.class);

                    // Mapear URLs de las imágenes a la propiedad DTO
                    List<String> imagenUrls = propiedad.getImagenes().stream()
                            .map(ImagenPropiedad::getUrl)
                            .collect(Collectors.toList());
                    propiedadDTO.setImagenes(imagenUrls);

                    return propiedadDTO;
                })
                .collect(Collectors.toList());
    }

    // Métodos adicionales
    public List<PropiedadDTO> buscarPropiedadesPorFiltros(String departamento, String municipio, Integer cantPersonas) {
        List<Propiedad> propiedadesFiltradas = obtenerPropiedadesFiltradas(departamento, municipio, cantPersonas);

        return propiedadesFiltradas.stream()
                .map(propiedad -> {
                    PropiedadDTO propiedadDTO = modelMapper.map(propiedad, PropiedadDTO.class);

                    // Mapear URLs de las imágenes a la propiedad DTO
                    List<String> imagenUrls = propiedad.getImagenes().stream()
                            .map(ImagenPropiedad::getUrl)
                            .collect(Collectors.toList());
                    propiedadDTO.setImagenes(imagenUrls);

                    return propiedadDTO;
                })
                .collect(Collectors.toList());
    }

    private List<Propiedad> obtenerPropiedadesFiltradas(String departamento, String municipio, Integer cantPersonas) {
        if (departamento != null && municipio != null && cantPersonas != null) {
            return propiedadRepository.findByDepartamentoAndMunicipioAndCantPersonas(departamento, municipio, cantPersonas);
        } else if (departamento != null && cantPersonas != null) {
            return propiedadRepository.findByDepartamentoAndCantPersonas(departamento, cantPersonas);
        } else if (departamento != null && municipio != null) {
            return propiedadRepository.findByDepartamentoAndMunicipio(departamento, municipio);
        } else if (departamento != null) {
            return propiedadRepository.findByDepartamento(departamento);
        } else if (municipio != null) {
            return propiedadRepository.findByMunicipio(municipio);
        } else if (cantPersonas != null) {
            return propiedadRepository.findByCantPersonas(cantPersonas);
        } else {
            return propiedadRepository.findAll();
        }
    }
}

