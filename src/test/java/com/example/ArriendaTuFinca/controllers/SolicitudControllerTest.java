/*package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;
import com.example.ArriendaTuFinca.services.SolicitudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SolicitudService solicitudService;

    @InjectMocks
    private SolicitudController solicitudController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(solicitudController).build();
    }

    @Test
    void testGetSolicitudes() throws Exception {
        List<SolicitudDTO> solicitudes = new ArrayList<>();
        when(solicitudService.get()).thenReturn(solicitudes);

        mockMvc.perform(get("/api/solicitudes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(solicitudService, times(1)).get();
    }

    @Test
    void testObtenerSolicitudPorId() throws Exception {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        when(solicitudService.obtenerSolicitudPorId(1L)).thenReturn(solicitudDTO);

        mockMvc.perform(get("/api/solicitudes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(solicitudService, times(1)).obtenerSolicitudPorId(1L);
    }

    @Test
    void testCrearSolicitud() throws Exception {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        when(solicitudService.crearSolicitud(any(SolicitudDTO.class))).thenReturn(solicitudDTO);

        mockMvc.perform(post("/api/solicitudes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(solicitudService, times(1)).crearSolicitud(any(SolicitudDTO.class));
    }

    @Test
    void testEliminarSolicitudPorId() throws Exception {
        doNothing().when(solicitudService).eliminarSolicitudPorId(1L);

        mockMvc.perform(delete("/api/solicitudes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(solicitudService, times(1)).eliminarSolicitudPorId(1L);
    }
}
*/