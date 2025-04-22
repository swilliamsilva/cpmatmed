package com.cpmatmed.backend.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.service.FornecedorService;
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
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FornecedorService fornecedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<FornecedorDTO> fornecedores;

    @BeforeEach
    void setUp() {
        FornecedorDTO f1 = new FornecedorDTO();
        f1.setId(1L);
        f1.setNome("Fornecedor A");

        FornecedorDTO f2 = new FornecedorDTO();
        f2.setId(2L);
        f2.setNome("Fornecedor B");

        fornecedores = Arrays.asList(f1, f2);
    }

    @Test
    void deveRetornarListaDeFornecedores() throws Exception {
        when(fornecedorService.listarTodos()).thenReturn(fornecedores);

        mockMvc.perform(get("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(fornecedores.size())))
               .andExpect(jsonPath("$[0].nome", is("Fornecedor A")))
               .andExpect(jsonPath("$[1].nome", is("Fornecedor B")));
    }

    @Test
    void deveRetornarErroQuandoBancoInacessivel() throws Exception {
        doThrow(new DataAccessResourceFailureException("Erro de conexão com o banco"))
            .when(fornecedorService).listarTodos();

        mockMvc.perform(get("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is5xxServerError())
               .andExpect(result -> {
                   Exception ex = result.getResolvedException();
                   assertNotNull(ex);
                   assertTrue(ex.getMessage().contains("Erro de conexão"));
               });
    }
}
