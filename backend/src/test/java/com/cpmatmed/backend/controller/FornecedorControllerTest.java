package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.service.FornecedorService;
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

@WebMvcTest(FornecedorController.class)
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FornecedorService fornecedorService;

    @Test
    public void deveListarTodosOsFornecedores() throws Exception {
        FornecedorDTO fornecedor1 = new FornecedorDTO(1L, "Fornecedor A");
        FornecedorDTO fornecedor2 = new FornecedorDTO(2L, "Fornecedor B");

        when(fornecedorService.listarTodos()).thenReturn(Arrays.asList(fornecedor1, fornecedor2));

        mockMvc.perform(get("/api/fornecedores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Fornecedor A"))
                .andExpect(jsonPath("$[1].nome").value("Fornecedor B"));
    }
}
