package com.example.ArriendaTuFinca.services;

import java.util.*;
import java.util.stream.Collectors;

import com.example.ArriendaTuFinca.models.Imagen;
import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.example.ArriendaTuFinca.repository.ImagenRepository;
import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.DTOs.ImagenDTO;


@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImagenRepository imagenRepository;

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

//    public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO) {
//        // Mapea el DTO a la entidad (model Mapper)
//        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
//        propiedad.setEstado(Estado.ACTIVE);
//
//        // Obtener el objeto UsuarioDTO del DTO de Propiedad
//        UsuarioDTO arrendadorDTO = propiedadDTO.getArrendador_id();
//        Long arrendadorId = arrendadorDTO.getUsuario_id();
//
//        Optional<Usuario> usuarioOptional = usuarioRepository.findById(arrendadorId); //del repo de Usu
//        if (usuarioOptional.isPresent()) {
//            // Usar el usuario existente en lugar de crear uno nuevo
//            propiedad.setArrendador_id(usuarioOptional.get());    //trae todo el usu
//
//            // Actualizar el DTO con el ID generado de la propiedad
//            propiedadDTO.setPropiedad_id(propiedad.getPropiedad_id());
//            return propiedadDTO;
//        }
//
//        // Manejar el caso en que el usuario no exista
//        throw new IllegalArgumentException("El arrendador con ID " + arrendadorId + " no existe.");
//    }


    public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO) {
        // Mapea el DTO a la entidad, excepto las imágenes
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setEstado(Estado.ACTIVE);

        // Obtener el arrendador por su ID
        UsuarioDTO arrendadorDTO = propiedadDTO.getArrendador_id();
        Long arrendadorId = arrendadorDTO.getUsuario_id();

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(arrendadorId);
        if (!usuarioOptional.isPresent()) {
            throw new IllegalArgumentException("El arrendador con ID " + arrendadorId + " no existe.");
        }

        propiedad.setArrendador_id(usuarioOptional.get());

        // Manejar las imágenes manualmente
        List<Imagen> imagenesActualizadas = new ArrayList<>();
        for (ImagenDTO imagenDTO : propiedadDTO.getImagenes()) {
            Optional<Imagen> imagenOptional = imagenRepository.findById(imagenDTO.getImagen_id());
            if (imagenOptional.isPresent()) {
                Imagen imagenExistente = imagenOptional.get();
                imagenExistente.setPropiedad(propiedad); // Asignar la propiedad a la imagen
                imagenesActualizadas.add(imagenExistente);
            } else {
                throw new IllegalArgumentException("La imagen con ID " + imagenDTO.getImagen_id() + " no existe.");
            }
        }

        propiedad.setImagenes(imagenesActualizadas);  // Asignar las imágenes actualizadas a la propiedad

        // Guardar la propiedad
        Propiedad nuevaPropiedad = propiedadRepository.save(propiedad);

        // Mapear de vuelta a DTO
        PropiedadDTO propiedadCreadaDTO = modelMapper.map(nuevaPropiedad, PropiedadDTO.class);
        return propiedadCreadaDTO;
    }
        
    //put
    //lo que no llegue se pone null
//    public PropiedadDTO actualizarPropiedad(Long id, PropiedadDTO propiedadDTO) {
//        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
//        propiedad.setPropiedad_id(id);
//        propiedadDTO = modelMapper.map(propiedadRepository.save(propiedad), PropiedadDTO.class);
//        return propiedadDTO;
//    }

    public PropiedadDTO actualizarPropiedad(Long propiedadId, PropiedadDTO propiedadDTO) {
        // Busca la propiedad en la base de datos
        Optional<Propiedad> optionalPropiedad = propiedadRepository.findById(propiedadId);

        if (optionalPropiedad.isPresent()) {
            // Obtén la entidad propiedad existente
            Propiedad propiedad = optionalPropiedad.get();

            // Mapear los valores del DTO a la entidad
            propiedad.setNombre(propiedadDTO.getNombre());
            propiedad.setDepartamento(propiedadDTO.getDepartamento());
            propiedad.setMunicipio(propiedadDTO.getMunicipio());
            propiedad.setTipo_de_ingreso(propiedadDTO.getTipo_de_ingreso());
            propiedad.setCant_banos(propiedadDTO.getCant_banos());
            propiedad.setCant_habitaciones(propiedadDTO.getCant_habitaciones());
            propiedad.setDescripcion(propiedadDTO.getDescripcion());
            propiedad.setCantPersonas(propiedadDTO.getCant_personas());
            propiedad.setMascotas(propiedadDTO.isMascotas());
            propiedad.setPiscina(propiedadDTO.isPiscina());
            propiedad.setAsador(propiedadDTO.isAsador());
            propiedad.setValor_noche(propiedadDTO.getValor_noche());

            //la calificaion se puede hacer en otro lado como para no actualizar todo

            // Actualizar la lista de imágenes sin reemplazarla directamente
            List<Imagen> imagenesActuales = propiedad.getImagenes();
            List<ImagenDTO> nuevasImagenesDTO = propiedadDTO.getImagenes();

            // Eliminar imágenes que ya no están en el DTO
            imagenesActuales.removeIf(imagen -> nuevasImagenesDTO.stream()
                    .noneMatch(imagenDTO -> imagenDTO.getImagen_id().equals(imagen.getImagen_id())));

            // Agregar o actualizar las imágenes del DTO
            for (ImagenDTO imagenDTO : nuevasImagenesDTO) {
                Imagen imagen = imagenRepository.findById(imagenDTO.getImagen_id())
                        .orElse(new Imagen()); // Si no existe, crea una nueva

                imagen.setUrl(imagenDTO.getUrl());
                imagen.setPropiedad(propiedad); // Asignar la propiedad a la imagen

                // Si la imagen no está en la lista actual, agregarla
                if (!imagenesActuales.contains(imagen)) {
                    imagenesActuales.add(imagen);
                }
            }

            // Guardar la propiedad actualizada
            propiedadRepository.save(propiedad);

            // Mapear la entidad actualizada de vuelta a un DTO
            return mapPropiedadToDTO(propiedad);
        } else {
            // Si la propiedad no existe, devolver un DTO vacío o null
            return null; // O puedes devolver un DTO vacío como nueva PropiedadDTO()
        }
    }

    private PropiedadDTO mapPropiedadToDTO(Propiedad propiedad) {
        PropiedadDTO propiedadDTO = new PropiedadDTO();

        // Mapear los campos de la entidad a los campos del DTO
        propiedadDTO.setPropiedad_id(propiedad.getPropiedad_id());
        propiedadDTO.setNombre(propiedad.getNombre());
        propiedadDTO.setDepartamento(propiedad.getDepartamento());
        propiedadDTO.setMunicipio(propiedad.getMunicipio());
        propiedadDTO.setCant_banos(propiedad.getCant_banos());
        propiedadDTO.setCant_habitaciones(propiedad.getCant_habitaciones());
        propiedadDTO.setValor_noche(propiedad.getValor_noche());
        propiedadDTO.setDescripcion(propiedad.getDescripcion());

        // Mapear las imágenes de la entidad a los DTOs de imágenes
        List<ImagenDTO> imagenesDTO = new ArrayList<>();
        for (Imagen imagen : propiedad.getImagenes()) {
            ImagenDTO imagenDTO = new ImagenDTO();
            imagenDTO.setImagen_id(imagen.getImagen_id());
            imagenDTO.setUrl(imagen.getUrl());
            imagenesDTO.add(imagenDTO);
        }
        propiedadDTO.setImagenes(imagenesDTO);

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
}

