package com.example.ArriendaTuFinca.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
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

    //User details service
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return usuarioRepository.findByCorreo(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }



    // Método para autenticar un usuario por correo y contraseña
    public UsuarioDTO autenticarUsuario(String correo, String contrasenia) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getAutenticado()) {
                return modelMapper.map(usuario, UsuarioDTO.class); // El usuario está autenticado
            } else {
                // El usuario no ha autenticado su cuenta
                throw new IllegalArgumentException("El usuario debe autenticar su cuenta desde el correo electrónico.");
            }
        } else {
            throw new IllegalArgumentException("Correo o contraseña incorrectos.");
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
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo); // asumiendo que tienes un método en el repositorio
        return usuarioOpt.isPresent();
    }


    // Método actualizado para crear usuario
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        if (correoExiste(usuarioDTO.getCorreo())) {
            throw new IllegalArgumentException("usuario ya existente");
        }

        // Validaciones de correo y contraseña

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        //usuario.setContrasenia(passwordEncoder.encode(usuarioDTO.getContrasenia())); // Encriptar la contraseña
        usuario = usuarioRepository.save(usuario);

        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

        // Enviar el correo de activación
        enviarCorreoActivacion(usuario);

        return usuarioDTO;
    }

    // Método para activar la cuenta de usuario
    public UsuarioDTO activarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
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
            String activationUrl = baseUrl + "/api/usuarios/activar/" + usuario.getUsuarioId();
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

    //Metodo para obtener tipo de usuario
    public boolean obtenerTipoUsuario(Long id) {
        System.out.println("entra servicio tipo usuario");
        Optional<Usuario> usuario = usuarioRepository.findByUsuarioId(id);

        // Si el usuario no está presente, lanzamos excepción
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("El usuario no existe.");
        }

        // Verificamos si el usuario es arrendador
        System.out.println("usuario: " + usuario.get().getUsuarioId());
        if (Objects.equals(usuario.get().getRol(), "arrendador")) {
            System.out.println("es arrendador true");
            return true;
        } else {
            System.out.println("es arrendatario false");
            return false;  // Cambiamos a false en lugar de lanzar excepción
        }
    }


}