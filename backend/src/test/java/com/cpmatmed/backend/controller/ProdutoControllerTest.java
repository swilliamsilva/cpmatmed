// src/test/java/com/cpmatmed/backend/controller/ProdutoControllerTest.java
package com.cpmatmed.backend.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
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
        ProdutoDTO p1 = new ProdutoDTO();
        p1.setId(1L);
        p1.setNome("Dipirona");
        p1.setQuantidade(10);
        p1.setPrecoUnitario(new BigDecimal("5.0"));
        p1.setFornecedorId(1L);

        ProdutoDTO p2 = new ProdutoDTO();
        p2.setId(2L);
        p2.setNome("Paracetamol");
        p2.setQuantidade(20);
        p2.setPrecoUnitario(new BigDecimal("3.5"));
        p2.setFornecedorId(2L);

        produtos = Arrays.asList(p1, p2);
    }

    @Test
    void deveRetornarListaDeProdutos() throws Exception {
        when(produtoService.listarTodosProdutos()).thenReturn(produtos);

        mockMvc.perform(get("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(produtos.size())))
               .andExpect(jsonPath("$[0].nome", is("Dipirona")))
               .andExpect(jsonPath("$[1].nome", is("Paracetamol")));
    }

    @Test
    void deveRetornarErroQuandoBancoInacessivel() throws Exception {
        doThrow(new RuntimeException("Erro ao acessar banco de dados"))
            .when(produtoService).listarTodosProdutos();

        mockMvc.perform(get("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is5xxServerError())
               .andExpect(result -> {
                   Exception ex = result.getResolvedException();
                   assertNotNull(ex);
                   assertTrue(ex.getMessage().contains("Erro ao acessar banco"));
               });
    }

    @Test
    void deveSalvarProduto() throws Exception {
        ProdutoDTO novo = new ProdutoDTO();
        novo.setNome("Ibuprofeno");
        novo.setQuantidade(15);
        novo.setPrecoUnitario(new BigDecimal("6.5"));
        novo.setFornecedorId(1L);

        ProdutoDTO salvo = new ProdutoDTO();
        salvo.setId(3L);
        salvo.setNome("Ibuprofeno");
        salvo.setQuantidade(15);
        salvo.setPrecoUnitario(new BigDecimal("6.5"));
        salvo.setFornecedorId(1L);

        when(produtoService.salvarProduto(any(ProdutoDTO.class))).thenReturn(salvo);

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(3L))
               .andExpect(jsonPath("$.nome").value("Ibuprofeno"));
    }

    @Test
    void deveAtualizarProduto() throws Exception {
        Long id = 1L;
        ProdutoDTO atual = new ProdutoDTO();
        atual.setNome("Dipirona Genérica");
        atual.setDescricao("Nova versão");
        atual.setPrecoUnitario(new BigDecimal("4.5"));
        atual.setQuantidade(12);
        atual.setFornecedorId(1L);

        ProdutoDTO retorno = new ProdutoDTO();
        retorno.setId(id);
        retorno.setNome("Dipirona Genérica");
        retorno.setDescricao("Nova versão");
        retorno.setPrecoUnitario(new BigDecimal("4.5"));
        retorno.setQuantidade(12);
        retorno.setFornecedorId(1L);

        when(produtoService.atualizarProduto(eq(id), any(ProdutoDTO.class))).thenReturn(retorno);

        mockMvc.perform(put("/api/produtos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atual)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nome").value("Dipirona Genérica"));
    }

    @Test
    void deveExcluirProduto() throws Exception {
        Long id = 1L;
        doNothing().when(produtoService).excluirProduto(id);

        mockMvc.perform(delete("/api/produtos/{id}", id))
               .andExpect(status().isNoContent());
    }
}
