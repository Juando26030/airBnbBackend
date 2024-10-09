/*package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.services.PropiedadService;
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

class PropiedadControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PropiedadService propiedadService;

    @InjectMocks
    private PropiedadController propiedadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(propiedadController).build();
    }

    @Test
    void testGetPropiedades() throws Exception {
        List<PropiedadDTO> propiedades = new ArrayList<>();
        when(propiedadService.get()).thenReturn(propiedades);

        mockMvc.perform(get("/api/propiedades")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(propiedadService, times(1)).get();
    }

    @Test
    void testObtenerPropiedadPorId() throws Exception {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        when(propiedadService.obtenerPropiedadPorId(1L)).thenReturn(propiedadDTO);

        mockMvc.perform(get("/api/propiedades/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(propiedadService, times(1)).obtenerPropiedadPorId(1L);
    }

    @Test
    void testCrearPropiedad() throws Exception {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        when(propiedadService.crearPropiedad(any(PropiedadDTO.class))).thenReturn(propiedadDTO);

        mockMvc.perform(post("/api/propiedades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(propiedadService, times(1)).crearPropiedad(any(PropiedadDTO.class));
    }

    @Test
    void testActualizarPropiedad() throws Exception {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        when(propiedadService.actualizarPropiedad(eq(1L), any(PropiedadDTO.class))).thenReturn(propiedadDTO);

        mockMvc.perform(put("/api/propiedades/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(propiedadService, times(1)).actualizarPropiedad(eq(1L), any(PropiedadDTO.class));
    }

    @Test
    void testEliminarPropiedad() throws Exception {
        doNothing().when(propiedadService).eliminarPropiedad(1L);

        mockMvc.perform(delete("/api/propiedades/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(propiedadService, times(1)).eliminarPropiedad(1L);
    }
}
*/