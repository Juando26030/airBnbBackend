/*package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.DTOs.CalificacionDTO;
import com.example.ArriendaTuFinca.services.CalificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CalificacionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CalificacionService calificacionService;

    @InjectMocks
    private CalificacionController calificacionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(calificacionController).build();
    }

    @Test
    void testGetCalificaciones() throws Exception {
        List<CalificacionDTO> calificaciones = new ArrayList<>();
        when(calificacionService.get()).thenReturn(calificaciones);

        mockMvc.perform(get("/api/calificaciones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(calificacionService, times(1)).get();
    }

    @Test
    void testObtenerCalificacionPorId() throws Exception {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        when(calificacionService.obtenerCalificacionPorId(1L)).thenReturn(calificacionDTO);

        mockMvc.perform(get("/api/calificaciones/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(calificacionService, times(1)).obtenerCalificacionPorId(1L);
    }

    @Test
    void testCrearCalificacion() throws Exception {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        when(calificacionService.crearCalificacion(any(CalificacionDTO.class))).thenReturn(calificacionDTO);

        mockMvc.perform(post("/api/calificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(calificacionService, times(1)).crearCalificacion(any(CalificacionDTO.class));
    }

    @Test
    void testActualizarCalificacion() throws Exception {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        when(calificacionService.actualizarCalificacion(any(CalificacionDTO.class))).thenReturn(calificacionDTO);

        mockMvc.perform(put("/api/calificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(calificacionService, times(1)).actualizarCalificacion(any(CalificacionDTO.class));
    }

    @Test
    void testEliminarCalificacionPorId() throws Exception {
        doNothing().when(calificacionService).eliminarCalificacionPorId(1L);

        mockMvc.perform(delete("/api/calificaciones/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(calificacionService, times(1)).eliminarCalificacionPorId(1L);
    }
}
*/