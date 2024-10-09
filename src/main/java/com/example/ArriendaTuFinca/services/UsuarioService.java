package com.example.ArriendaTuFinca.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // Método para autenticar un usuario por correo y contraseña
    public UsuarioDTO autenticarUsuario(String correo, String contrasenia) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getAutenticado()) {
                return modelMapper.map(usuario, UsuarioDTO.class); // El usuario está autenticado
            } else {
                throw new IllegalArgumentException("Debe autenticar su usuario antes de iniciar sesión.");
            }
        } else {
            return null; // Retornar null si no existe un usuario con esas credenciales
        }
    }


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
    // Código existente...

    public boolean correoExiste(String correo) {
        return usuarioRepository.findByCorreo(correo).isPresent();
    }

    // Método actualizado para crear usuario
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        if (correoExiste(usuarioDTO.getCorreo())) {
            throw new IllegalArgumentException("usuario ya existente");
        }

        // Validaciones de correo y contraseña

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setEstado(Estado.INACTIVE); // El usuario se crea como inactivo por defecto
        usuario.setAutenticado(false); // Usuario no autenticado por defecto
        usuario = usuarioRepository.save(usuario);

        // Enviar el correo de activación
        enviarCorreoActivacion(usuario);

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    // Método para activar la cuenta de usuario
    public UsuarioDTO activarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setEstado(Estado.ACTIVE); // Cambiar estado a activo
            usuario.setAutenticado(true); // Marcar como autenticado
            usuario = usuarioRepository.save(usuario); // Guardar cambios en la base de datos
            return modelMapper.map(usuario, UsuarioDTO.class);
        } else {
            throw new IllegalArgumentException("El usuario no existe.");
        }
    }

    private void enviarCorreoActivacion(Usuario usuario) {
        try {
            String subject = "Por favor active su cuenta";
            // Usar un valor configurable para la URL base
            String baseUrl = "http://localhost:8081"; // Puedes externalizar esto a application.properties
            String activationUrl = baseUrl + "/api/usuarios/activar/" + usuario.getUsuario_id();
            String message = "Por favor active su cuenta haciendo clic en el siguiente enlace: " + activationUrl;

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(usuario.getCorreo());
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailMessage.setFrom(fromEmail);

            mailSender.send(mailMessage);
        } catch (MailException e) {
            // Manejo de la excepción
            System.out.println("Error al enviar correo: " + e.getMessage());
        }
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