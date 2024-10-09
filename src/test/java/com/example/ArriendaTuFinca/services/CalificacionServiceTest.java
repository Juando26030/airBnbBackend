/*package com.example.ArriendaTuFinca.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.ArriendaTuFinca.repository.CalificacionRepository;
import com.example.ArriendaTuFinca.repository.SolicitudRepository;
import com.example.ArriendaTuFinca.models.Calificacion;
import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.DTOs.CalificacionDTO;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;

@ExtendWith(MockitoExtension.class)
class CalificacionServiceTest {

    @Mock
    private CalificacionRepository calificacionRepository;

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CalificacionService calificacionService;

    private Calificacion calificacion;
    private CalificacionDTO calificacionDTO;
    private Solicitud solicitud;
    private SolicitudDTO solicitudDTO;

    @BeforeEach
    void setUp() {
        calificacion = new Calificacion();
        calificacion.setCalificacion_id(1L);

        solicitud = new Solicitud();
        solicitud.setSolicitud_id(1L);

        calificacionDTO = new CalificacionDTO();
        calificacionDTO.setCalificacion_id(1L);

        solicitudDTO = new SolicitudDTO();
        solicitudDTO.setSolicitud_id(1L);

        calificacionDTO.setSolicitud_id(solicitudDTO);
    }

    @Test
    void testGet() {
        // Mocking repository and model mapper
        when(calificacionRepository.findAll()).thenReturn(Arrays.asList(calificacion));
        when(modelMapper.map(calificacion, CalificacionDTO.class)).thenReturn(calificacionDTO);

        // Call the service method
        List<CalificacionDTO> result = calificacionService.get();

        // Assert the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(calificacionDTO, result.get(0));

        // Verify repository interaction
        verify(calificacionRepository).findAll();
    }

    @Test
    void testObtenerCalificacionPorId_Exists() {
        when(calificacionRepository.findById(1L)).thenReturn(Optional.of(calificacion));
        when(modelMapper.map(calificacion, CalificacionDTO.class)).thenReturn(calificacionDTO);

        CalificacionDTO result = calificacionService.obtenerCalificacionPorId(1L);

        assertNotNull(result);
        assertEquals(calificacionDTO, result);

        verify(calificacionRepository).findById(1L);
    }

    @Test
    void testObtenerCalificacionPorId_NotExists() {
        when(calificacionRepository.findById(1L)).thenReturn(Optional.empty());

        CalificacionDTO result = calificacionService.obtenerCalificacionPorId(1L);

        assertNull(result);

        verify(calificacionRepository).findById(1L);
    }

    @Test
    void testCrearCalificacion_SolicitudExists() {
        lenient().when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        lenient().when(modelMapper.map(calificacionDTO, Calificacion.class)).thenReturn(calificacion);
        lenient().when(calificacionRepository.save(calificacion)).thenReturn(calificacion);
        lenient().when(modelMapper.map(calificacion, CalificacionDTO.class)).thenReturn(calificacionDTO);

        CalificacionDTO result = calificacionService.crearCalificacion(calificacionDTO);

        assertNotNull(result);
        assertEquals(1L, result.getCalificacion_id());

        verify(solicitudRepository).findById(1L);
        verify(calificacionRepository).save(calificacion);
    }

    @Test
    void testCrearCalificacion_SolicitudNotExists() {
        when(solicitudRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            calificacionService.crearCalificacion(calificacionDTO);
        });

        assertEquals("No se encontro la solicitud con el id: 1", exception.getMessage());

        verify(solicitudRepository).findById(1L);
        verify(calificacionRepository, never()).save(any(Calificacion.class));
    }

    @Test
    void testActualizarCalificacion_SolicitudExists() {
        lenient().when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        lenient().when(modelMapper.map(calificacionDTO, Calificacion.class)).thenReturn(calificacion);
        lenient().when(calificacionRepository.save(calificacion)).thenReturn(calificacion);
        lenient().when(modelMapper.map(calificacion, CalificacionDTO.class)).thenReturn(calificacionDTO);

        CalificacionDTO result = calificacionService.actualizarCalificacion(calificacionDTO);

        assertNotNull(result);
        assertEquals(1L, result.getCalificacion_id());

        verify(solicitudRepository).findById(1L);
        verify(calificacionRepository).save(calificacion);
    }

    @Test
    void testActualizarCalificacion_SolicitudNotExists() {
        when(solicitudRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            calificacionService.actualizarCalificacion(calificacionDTO);
        });

        assertEquals("No se encontro la solicitud con el id: 1", exception.getMessage());

        verify(solicitudRepository).findById(1L);
        verify(calificacionRepository, never()).save(any(Calificacion.class));
    }

    @Test
    void testEliminarCalificacion() {
        when(modelMapper.map(calificacionDTO, Calificacion.class)).thenReturn(calificacion);

        calificacionService.eliminarCalificacion(calificacionDTO);

        verify(calificacionRepository).delete(calificacion);
    }

    @Test
    void testEliminarCalificacionPorId() {
        calificacionService.eliminarCalificacionPorId(1L);

        verify(calificacionRepository).deleteById(1L);
    }
}
*/