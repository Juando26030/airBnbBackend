/*package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.DTOs.PagoDTO;
import com.example.ArriendaTuFinca.services.PagoService;
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

class PagoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PagoService pagoService;

    @InjectMocks
    private PagoController pagoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagoController).build();
    }

    @Test
    void testGetPagos() throws Exception {
        List<PagoDTO> pagos = new ArrayList<>();
        when(pagoService.get()).thenReturn(pagos);

        mockMvc.perform(get("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(pagoService, times(1)).get();
    }

    @Test
    void testObtenerPagoPorId() throws Exception {
        PagoDTO pagoDTO = new PagoDTO();
        when(pagoService.obtenerPagoPorId(1L)).thenReturn(pagoDTO);

        mockMvc.perform(get("/api/pagos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(pagoService, times(1)).obtenerPagoPorId(1L);
    }

    @Test
    void testCrearPago() throws Exception {
        PagoDTO pagoDTO = new PagoDTO();
        when(pagoService.crearPago(any(PagoDTO.class))).thenReturn(pagoDTO);

        mockMvc.perform(post("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(pagoService, times(1)).crearPago(any(PagoDTO.class));
    }

    @Test
    void testActualizarPago() throws Exception {
        PagoDTO pagoDTO = new PagoDTO();
        when(pagoService.actualizarPago(any(PagoDTO.class))).thenReturn(pagoDTO);

        mockMvc.perform(put("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(pagoService, times(1)).actualizarPago(any(PagoDTO.class));
    }

    @Test
    void testEliminarPagoPorId() throws Exception {
        doNothing().when(pagoService).eliminarPagoPorId(1L);

        mockMvc.perform(delete("/api/pagos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(pagoService, times(1)).eliminarPagoPorId(1L);
    }
}
*/