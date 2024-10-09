/*package com.example.ArriendaTuFinca.repository;

import com.example.ArriendaTuFinca.models.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindByCorreoAndContrasenia() {
        // Preparar datos de prueba
        String correo = "test@example.com";
        String contrasenia = "password123";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasenia(contrasenia);

        // Definir el comportamiento del mock
        when(usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia)).thenReturn(Optional.of(usuario));

        // Llamar al método que estamos probando
        Optional<Usuario> resultado = usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia);

        // Verificar el resultado
        assertTrue(resultado.isPresent());
        assertEquals(correo, resultado.get().getCorreo());
        assertEquals(contrasenia, resultado.get().getContrasenia());

        // Verificar que se llamó al método correcto en el mock
        verify(usuarioRepository, times(1)).findByCorreoAndContrasenia(correo, contrasenia);
    }

    @Test
    public void testFindByCorreo() {
        // Preparar datos de prueba
        String correo = "test@example.com";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);

        // Definir el comportamiento del mock
        when(usuarioRepository.findByCorreo(correo)).thenReturn(Optional.of(usuario));

        // Llamar al método que estamos probando
        Optional<Usuario> resultado = usuarioRepository.findByCorreo(correo);

        // Verificar el resultado
        assertTrue(resultado.isPresent());
        assertEquals(correo, resultado.get().getCorreo());

        // Verificar que se llamó al método correcto en el mock
        verify(usuarioRepository, times(1)).findByCorreo(correo);
    }

    @Test
    public void testFindByCorreoAndContrasenia_NoResult() {
        // Preparar datos de prueba
        String correo = "test@example.com";
        String contrasenia = "wrongpassword";

        // Definir el comportamiento del mock
        when(usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia)).thenReturn(Optional.empty());

        // Llamar al método que estamos probando
        Optional<Usuario> resultado = usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia);

        // Verificar el resultado
        assertFalse(resultado.isPresent());

        // Verificar que se llamó al método correcto en el mock
        verify(usuarioRepository, times(1)).findByCorreoAndContrasenia(correo, contrasenia);
    }

    @Test
    public void testFindByCorreo_NoResult() {
        // Preparar datos de prueba
        String correo = "nonexistent@example.com";

        // Definir el comportamiento del mock
        when(usuarioRepository.findByCorreo(correo)).thenReturn(Optional.empty());

        // Llamar al método que estamos probando
        Optional<Usuario> resultado = usuarioRepository.findByCorreo(correo);

        // Verificar el resultado
        assertFalse(resultado.isPresent());

        // Verificar que se llamó al método correcto en el mock
        verify(usuarioRepository, times(1)).findByCorreo(correo);
    }
}
*/