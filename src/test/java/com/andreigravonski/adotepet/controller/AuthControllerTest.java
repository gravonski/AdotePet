package com.andreigravonski.adotepet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest // 1. Carrega o contexto COMPLETO da sua aplicação Spring
@AutoConfigureMockMvc // 2. Configura e nos dá acesso ao MockMvc
class AuthControllerTest {

    @Autowired // 3. Injeta o simulador de navegador
    private MockMvc mockMvc;

    @Test
    void deveRetornarPaginaDeRegistroComSucesso() throws Exception {
        // ACT (Agir) & ASSERT (Verificar)

        // Simula uma requisição GET para a URL "/registro"
        mockMvc.perform(get("/registro"))
                .andExpect(status().isOk()) // E ESPERA (andExpect) que o status da resposta seja 200 OK
                .andExpect(view().name("registro")); // E ESPERA que a view retornada pelo controller seja a "registro.html"
    }
}