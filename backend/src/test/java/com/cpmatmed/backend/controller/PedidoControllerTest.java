package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private PedidoDTO pedidoDTO;

    @BeforeEach
    void setup() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNome("Dipirona");
        produtoDTO.setPrecoUnitario(new BigDecimal("10.00"));
        produtoDTO.setQuantidade(2);
        produtoDTO.setFornecedorId(1L);

        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(1L);
        pedidoDTO.setDescricao("Pedido Teste");
        pedidoDTO.setProdutos(Arrays.asList(produtoDTO));
    }

    @Test
    void deveRetornarListaDePedidos() throws Exception {
        when(pedidoService.listarTodosPedidos()).thenReturn(Arrays.asList(pedidoDTO));

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].descricao", is("Pedido Teste")));
    }

    @Test
    void deveBuscarPedidoPorIdExistente() throws Exception {
        when(pedidoService.buscarPedidoPorId(1L)).thenReturn(pedidoDTO);

        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.descricao", is("Pedido Teste")));
    }

    @Test
    void deveRetornar404ParaPedidoInexistente() throws Exception {
        when(pedidoService.buscarPedidoPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/pedidos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarProdutosDoPedido() throws Exception {
        when(pedidoService.listarProdutosDoPedido(1L)).thenReturn(pedidoDTO.getProdutos());

        mockMvc.perform(get("/pedidos/1/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Dipirona")))
                .andExpect(jsonPath("$[0].quantidade", is(2)));
    }

    @Test
    void deveRetornar404SePedidoNaoTiverProdutos() throws Exception {
        when(pedidoService.listarProdutosDoPedido(99L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/pedidos/99/produtos"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveCriarPedidoComSucesso() throws Exception {
        when(pedidoService.criarPedido(Mockito.any(PedidoDTO.class))).thenReturn(pedidoDTO);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.descricao", is("Pedido Teste")))
                .andExpect(jsonPath("$.produtos[0].nome", is("Dipirona")));
    }
}
