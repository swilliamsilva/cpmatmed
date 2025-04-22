package com.cpmatmed.backend.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.service.CompradorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class CompradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompradorService compradorService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<CompradorDTO> compradores;

    @BeforeEach
    void setUp() {
        CompradorDTO c1 = new CompradorDTO(1L, "Comprador A");
        CompradorDTO c2 = new CompradorDTO(2L, "Comprador B");

        compradores = Arrays.asList(c1, c2);
    }

    @Test
    void deveListarTodosOsCompradores() throws Exception {
        when(compradorService.listarTodos()).thenReturn(compradores);

        mockMvc.perform(get("/api/compradores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("Comprador A")))
                .andExpect(jsonPath("$[1].nome", is("Comprador B")));
    }

    @Test
    void deveRetornarErroQuandoBancoInacessivel() throws Exception {
        when(compradorService.listarTodos())
                .thenThrow(new DataAccessResourceFailureException("Banco inacessível"));

        mockMvc.perform(get("/api/compradores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(result -> {
                    Exception ex = result.getResolvedException();
                    assertNotNull(ex);
                    assertTrue(ex.getMessage().contains("Banco inacessível"));
                });
    }
}
