package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.service.ONGService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import com.andreigravonski.adotepet.model.ONG;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest { // <-- A CLASSE COMEÇA AQUI

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ONGService ongService;

    @Test
    void deveRetornarPaginaDeRegistroComSucesso() throws Exception {
        mockMvc.perform(get("/registro"))
                .andExpect(status().isOk())
                .andExpect(view().name("registro"));
    }

    @Test
    void deveProcessarRegistroDeNovaOngComSucesso() throws Exception {
        mockMvc.perform(post("/registro")
                        .param("nome", "ONG Teste")
                        .param("email", "teste@ong.com")
                        .param("senha", "senha123")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?registro=sucesso"));

        verify(ongService).registrarNovaOng(any(ONG.class));
    }

} // <-- A CLASSE TERMINA AQUI. SEU SEGUNDO TESTE ESTAVA PROVAVELMENTE DEPOIS DESTA CHAVE.