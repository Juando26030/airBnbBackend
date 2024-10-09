package com.example.ArriendaTuFinca.mappers;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.PropiedadRepository;

import org.modelmapper.convention.MatchingStrategies;

@Configuration
public class ModelMapperConfiguration {

    // Configuración de ModelMapper PROFESOR
    @Bean
    public ModelMapper moddelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }




    /* 
    @Autowired
    private PropiedadRepository propiedadRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Mapping UserDTO to User
        modelMapper.typeMap(UsuarioDTO.class, Usuario.class).addMappings(mapper -> {
            mapper.using(ctx -> {
                List<Long> source = (List<Long>) ctx.getSource();
                return source.stream()
                                        .map(propiedadId -> propiedadRepository.findById(propiedadId).orElse(null))
                                        .collect(Collectors.toList());
            });
        });

        // Mapping User to UserDTO
        modelMapper.typeMap(Usuario.class, UsuarioDTO.class).addMappings(mapper -> {
            mapper.using(ctx -> {
                List<Propiedad> source = (List<Propiedad>) ctx.getSource();
                return source.stream()
                                        .map(propiedad -> propiedad.getPropiedad_id())
                                        .collect(Collectors.toList());
            });
        });

        // Mapping PropiedadDTO to Propiedad
        modelMapper.typeMap(PropiedadDTO.class, Propiedad.class).addMappings(mapper -> {
            mapper.using(ctx -> {
                Long source = (Long) ctx.getSource();
                return propiedadRepository.findById(source).orElse(null);
            });
        });

        // Mapping Propiedad to PropiedadDTO
        modelMapper.typeMap(Propiedad.class, PropiedadDTO.class).addMappings(mapper -> {
            mapper.using(ctx -> {
                Propiedad source = (Propiedad) ctx.getSource();
                return source.getPropiedad_id();
            });
        });
        
        return modelMapper;
    }*/
}
