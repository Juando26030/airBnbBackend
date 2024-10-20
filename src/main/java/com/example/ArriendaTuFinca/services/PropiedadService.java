package com.example.ArriendaTuFinca.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

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
        propiedad.setEstado(ACTIVO);

        // Obtener el objeto UsuarioDTO del DTO de Propiedad
        UsuarioDTO arrendadorDTO = propiedadDTO.getArrendador_id();
        Long arrendadorId = arrendadorDTO.getUsuario_id();

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(arrendadorId); //del repo de Usu
        if (usuarioOptional.isPresent()) {
            // Usar el usuario existente en lugar de crear uno nuevo
            propiedad.setArrendador(usuarioOptional.get());    //trae todo el usu
            propiedad = propiedadRepository.save(propiedad);   //guarda la propiedad (persistencia)

            // Actualizar el DTO con el ID generado de la propiedad
            propiedadDTO.setPropiedad_id(propiedad.getPropiedad_id());
            return propiedadDTO;
        }

        // Manejar el caso en que el usuario no exista
        throw new IllegalArgumentException("El arrendador con ID " + arrendadorId + " no existe.");
    }
        
    //put
    public PropiedadDTO actualizarPropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setEstado(ACTIVO);
        propiedad.setPropiedad_id(id);
        propiedadDTO = modelMapper.map(propiedadRepository.save(propiedad), PropiedadDTO.class);
        return propiedadDTO;
    }

    //delete por id
    public void eliminarPropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }


    // Método que implementa la lógica de búsqueda por ubicación y/o cantidad de personas
    // Método que implementa la lógica de búsqueda por departamento, municipio y/o cantidad de personas
    public List<PropiedadDTO> buscarPropiedadesPorFiltros(String departamento, String municipio, Integer cantPersonas) {
        List<Propiedad> propiedadesFiltradas;

        // Si están presentes todos los filtros
        if (departamento != null && municipio != null && cantPersonas != null) {
            propiedadesFiltradas = propiedadRepository.findByDepartamentoAndMunicipioAndCantPersonas(departamento, municipio, cantPersonas);
        }
        // Si están presentes departamento y cantidad de personas (sin municipio)
        else if (departamento != null && cantPersonas != null) {
            propiedadesFiltradas = propiedadRepository.findByDepartamentoAndCantPersonas(departamento, cantPersonas);
        }
        // Si están presentes departamento y municipio (sin cantidad de personas)
        else if (departamento != null && municipio != null) {
            propiedadesFiltradas = propiedadRepository.findByDepartamentoAndMunicipio(departamento, municipio);
        }
        // Si solo está el departamento
        else if (departamento != null) {
            propiedadesFiltradas = propiedadRepository.findByDepartamento(departamento);
        }
        // Si solo está el municipio
        else if (municipio != null) {
            propiedadesFiltradas = propiedadRepository.findByMunicipio(municipio);
        }
        // Si solo está la cantidad de personas
        else if (cantPersonas != null) {
            propiedadesFiltradas = propiedadRepository.findByCantPersonas(cantPersonas);
        }
        // Si no se proporcionó ningún filtro, devolver todas las propiedades
        else {
            propiedadesFiltradas = propiedadRepository.findAll();
        }

        // Convertir las entidades a DTOs y devolver
        return propiedadesFiltradas.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());
    }

    // Método para buscar propiedades por el id del arrendador
    public List<PropiedadDTO> buscarPropiedadesAdmin(Long id_admin) {
        // Obtener propiedades asociadas al arrendador
        List<Propiedad> propiedades = propiedadRepository.findByArrendador_UsuarioId(id_admin);

        // Convertir entidades a DTO
        return propiedades.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());
    }
}

