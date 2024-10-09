/*package com.example.ArriendaTuFinca.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ViewsController.class)
class ViewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));  // Verifica que se devuelve la vista login.html
    }

    @Test
    void testCrearUsuario() throws Exception {
        mockMvc.perform(get("/crear-usuario"))
                .andExpect(status().isOk())
                .andExpect(view().name("crear-usuario"));  // Verifica que se devuelve la vista crear-usuario.html
    }

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));  // Verifica que se devuelve la vista index.html
    }

    @Test
    void testActivacion() throws Exception {
        mockMvc.perform(get("/activacion"))
                .andExpect(status().isOk())
                .andExpect(view().name("navegar"));  // Verifica que se devuelve la vista navegar.html
    }

    @Test
    void testUsuarioCreado() throws Exception {
        mockMvc.perform(get("/usuario-creado"))
                .andExpect(status().isOk())
                .andExpect(view().name("navegar"));  // Verifica que se devuelve la vista navegar.html
    }
}
*/