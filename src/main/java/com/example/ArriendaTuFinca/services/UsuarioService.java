package com.example.ArriendaTuFinca.services;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    //se van a retornar DTOs

    //get
    public List<UsuarioDTO> get() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream() //convertir lista de usuarios a lista de usuariosDTO
                                       .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                                       .collect(Collectors.toList());   
        return usuariosDTO;
    }

    //get por id
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id); //Optional es un contenedor que puede o no contener un valor no nulo
        UsuarioDTO usuarioDTO = null;
        if (usuario.isPresent()) {
            usuarioDTO = modelMapper.map(usuario.get(), UsuarioDTO.class);
        }
        return usuarioDTO;
    }

    //post
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setEstado(Estado.ACTIVE);
        usuario = usuarioRepository.save(usuario);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    //put
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setEstado(Estado.ACTIVE);
        usuario = usuarioRepository.save(usuario);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    //delete
    public void eliminarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioRepository.delete(usuario);
    }

    //delete por id
    public void eliminarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

}
