package com.cpmatmed.backend.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ProdutoDTO> produtos;

    @BeforeEach
    void setUp() {
        ProdutoDTO produto1 = new ProdutoDTO();
        produto1.setId(1L);
        produto1.setNome("Dipirona");
        produto1.setQuantidade(10);
        produto1.setPrecoUnitario(new BigDecimal("5.0")); // ✅ BigDecimal
        produto1.setFornecedorId(1L);

        ProdutoDTO produto2 = new ProdutoDTO();
        produto2.setId(2L);
        produto2.setNome("Paracetamol");
        produto2.setQuantidade(20);
        produto2.setPrecoUnitario(new BigDecimal("3.5")); // ✅ BigDecimal
        produto2.setFornecedorId(2L);

        produtos = Arrays.asList(produto1, produto2);
    }

    @Test
    void deveRetornarListaDeProdutos() throws Exception {
        when(produtoService.listarTodos()).thenReturn(produtos); // ✅ nome corrigido

        mockMvc.perform(get("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(produtos.size())))
                .andExpect(jsonPath("$[0].nome", is("Dipirona")))
                .andExpect(jsonPath("$[1].nome", is("Paracetamol")));
    }

    @Test
    void deveRetornarErroQuandoBancoInacessivel() throws Exception {
        doThrow(new DataAccessResourceFailureException("Erro ao acessar banco de dados"))
                .when(produtoService).listarTodos(); // ✅ nome corrigido

        mockMvc.perform(get("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(result ->
                        result.getResolvedException().getMessage().contains("Erro ao acessar banco"));
    }
}
