package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.PedidoResponse;
import com.cpmatmed.backend.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<PedidoResponse> pedidos;

    @BeforeEach
    void setUp() {
        PedidoResponse p1 = new PedidoResponse();
        p1.setId(1L);
        p1.setValorTotal(new BigDecimal("100.00"));
        p1.setNomeComprador("Comprador 1");
        p1.setNomeFornecedor("Fornecedor 1");

        PedidoResponse p2 = new PedidoResponse();
        p2.setId(2L);
        p2.setValorTotal(new BigDecimal("250.50"));
        p2.setNomeComprador("Comprador 2");
        p2.setNomeFornecedor("Fornecedor 2");

        pedidos = Arrays.asList(p1, p2);
    }

    @Test
    void deveRetornarListaDePedidos() throws Exception {
        when(pedidoService.listarPedidos()).thenReturn(pedidos);

        mockMvc.perform(get("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(pedidos.size())))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].valorTotal", closeTo(250.50, 0.001)));
    }

    @Test
    void deveRetornarErroQuandoBancoInacessivel() throws Exception {
        doThrow(new DataAccessResourceFailureException("Erro ao acessar banco de dados"))
                .when(pedidoService).listarPedidos();

        mockMvc.perform(get("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(result ->
                        result.getResolvedException().getMessage().contains("Erro ao acessar banco"));
    }
}
