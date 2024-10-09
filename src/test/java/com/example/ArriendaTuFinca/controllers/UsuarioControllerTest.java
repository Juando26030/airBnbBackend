/*package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private Model model;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsuarios() {
        List<UsuarioDTO> usuariosMock = List.of(new UsuarioDTO());
        when(usuarioService.get()).thenReturn(usuariosMock);

        List<UsuarioDTO> resultado = usuarioController.get();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(usuarioService, times(1)).get();
    }

    @Test
    void testLoginUsuarioAutenticado() {
        String correo = "test@example.com";
        String contrasenia = "password";
        UsuarioDTO usuarioMock = new UsuarioDTO();

        when(usuarioService.autenticarUsuario(correo, contrasenia)).thenReturn(usuarioMock);

        String vista = usuarioController.login(correo, contrasenia, model);

        assertEquals("redirect:/", vista);
        verify(model).addAttribute("usuario", usuarioMock);
    }

    @Test
    void testLoginUsuarioNoAutenticado() {
        String correo = "test@example.com";
        String contrasenia = "password";

        when(usuarioService.autenticarUsuario(correo, contrasenia)).thenReturn(null);

        String vista = usuarioController.login(correo, contrasenia, model);

        assertEquals("login", vista);
        verify(model).addAttribute("error", "Correo o contraseña incorrectos");
    }

    @Test
    void testLoginUsuarioExcepcion() {
        String correo = "test@example.com";
        String contrasenia = "password";

        when(usuarioService.autenticarUsuario(correo, contrasenia)).thenThrow(new IllegalArgumentException("Debe autenticar su usuario antes de iniciar sesión."));

        String vista = usuarioController.login(correo, contrasenia, model);

        assertEquals("login", vista);
        verify(model).addAttribute("error", "Debe autenticar su usuario antes de iniciar sesión.");
    }

    @Test
    void testObtenerUsuarioPorId() {
        Long id = 1L;
        UsuarioDTO usuarioMock = new UsuarioDTO();

        when(usuarioService.obtenerUsuarioPorId(id)).thenReturn(usuarioMock);

        UsuarioDTO resultado = usuarioController.obtenerUsuarioPorId(id);

        assertNotNull(resultado);
        verify(usuarioService, times(1)).obtenerUsuarioPorId(id);
    }

    @Test
    void testCrearUsuarioExitoso() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioDTO nuevoUsuarioMock = new UsuarioDTO();
        nuevoUsuarioMock.setCorreo("test@example.com");

        when(usuarioService.crearUsuario(usuarioDTO)).thenReturn(nuevoUsuarioMock);

        String vista = usuarioController.crearUsuario(usuarioDTO, model);

        assertEquals("usuario-creado", vista);
        verify(model).addAttribute("correo", nuevoUsuarioMock.getCorreo());
    }

    @Test
    void testCrearUsuarioError() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        when(usuarioService.crearUsuario(usuarioDTO)).thenThrow(new IllegalArgumentException("usuario ya existente"));

        String vista = usuarioController.crearUsuario(usuarioDTO, model);

        assertEquals("crear-usuario", vista);
        verify(model).addAttribute("error", "usuario ya existente");
    }

    @Test
    void testActivarUsuarioExitoso() {
        Long id = 1L;
        UsuarioDTO usuarioMock = new UsuarioDTO();

        when(usuarioService.activarUsuario(id)).thenReturn(usuarioMock);

        String vista = usuarioController.activarUsuario(id, model);

        assertEquals("activacion", vista);
    }

    @Test
    void testActivarUsuarioError() {
        Long id = 1L;

        when(usuarioService.activarUsuario(id)).thenThrow(new IllegalArgumentException("El usuario no existe."));

        String vista = usuarioController.activarUsuario(id, model);

        assertEquals("error", vista);
        verify(model).addAttribute("error", "El usuario no existe.");
    }

    @Test
    void testActualizarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioDTO usuarioActualizadoMock = new UsuarioDTO();

        when(usuarioService.actualizarUsuario(usuarioDTO)).thenReturn(usuarioActualizadoMock);

        UsuarioDTO resultado = usuarioController.actualizarUsuario(usuarioDTO);

        assertNotNull(resultado);
        verify(usuarioService, times(1)).actualizarUsuario(usuarioDTO);
    }

    @Test
    void testEliminarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioController.eliminarUsuario(usuarioDTO);

        verify(usuarioService, times(1)).eliminarUsuario(usuarioDTO);
    }

    @Test
    void testEliminarUsuarioPorId() {
        Long id = 1L;

        usuarioController.eliminarUsuario(id);

        verify(usuarioService, times(1)).eliminarUsuarioPorId(id);
    }
}
*/