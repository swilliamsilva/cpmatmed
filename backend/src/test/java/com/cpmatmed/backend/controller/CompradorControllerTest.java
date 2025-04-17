package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.service.CompradorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CompradorController.class)
public class CompradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompradorService compradorService;

    @Test
    public void deveListarTodosOsCompradores() throws Exception {
        CompradorDTO comprador1 = new CompradorDTO();
        comprador1.setId(1L);
        comprador1.setNome("Maria");

        CompradorDTO comprador2 = new CompradorDTO();
        comprador2.setId(2L);
        comprador2.setNome("João");

        when(compradorService.listarTodos()).thenReturn(Arrays.asList(comprador1, comprador2));

        mockMvc.perform(get("/api/compradores")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Maria"))
                .andExpect(jsonPath("$[1].nome").value("João"));
    }
}
