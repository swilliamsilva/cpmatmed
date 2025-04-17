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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PedidoController.class,
    includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = PedidoController.class))
@ContextConfiguration(classes = {PedidoController.class})
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private PedidoDTO pedidoDTO;

    @BeforeEach
    public void setup() {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setId(1L);
        produto.setNome("Dipirona");
        produto.setQuantidade(10);
        produto.setPrecoUnitario(new BigDecimal("2.5"));
        produto.setValorTotal(new BigDecimal("25.0"));

        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(1L);
        pedidoDTO.setCompradorId(1L);
        pedidoDTO.setFornecedorId(1L);
        pedidoDTO.setNomeComprador("Hospital Municipal");
        pedidoDTO.setNomeFornecedor("Fornecedor Teste");
        pedidoDTO.setTotalProdutos(1);
        pedidoDTO.setValorTotal(new BigDecimal("25.0"));
        pedidoDTO.setProdutos(Collections.singletonList(produto));
    }

    @Test
    public void testGetAllPedidos() throws Exception {
        Mockito.when(pedidoService.listarTodosPedidos()).thenReturn(Arrays.asList(pedidoDTO));

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(pedidoDTO.getId()))
                .andExpect(jsonPath("$[0].nomeComprador").value(pedidoDTO.getNomeComprador()))
                .andExpect(jsonPath("$[0].nomeFornecedor").value(pedidoDTO.getNomeFornecedor()))
                .andExpect(jsonPath("$[0].totalProdutos").value(pedidoDTO.getTotalProdutos()))
                .andExpect(jsonPath("$[0].valorTotal").value(pedidoDTO.getValorTotal().doubleValue()));
    }

    @Test
    public void testCreatePedido() throws Exception {
        Mockito.when(pedidoService.criarPedido(any(PedidoDTO.class))).thenReturn(pedidoDTO);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pedidoDTO.getId()))
                .andExpect(jsonPath("$.nomeComprador").value(pedidoDTO.getNomeComprador()))
                .andExpect(jsonPath("$.nomeFornecedor").value(pedidoDTO.getNomeFornecedor()))
                .andExpect(jsonPath("$.totalProdutos").value(pedidoDTO.getTotalProdutos()))
                .andExpect(jsonPath("$.valorTotal").value(pedidoDTO.getValorTotal().doubleValue()));
    }
}
