package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.service.ProdutoService;
import org.junit.jupiter.api.Test;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProdutoController.class,
    includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ProdutoController.class))
@ContextConfiguration(classes = {ProdutoController.class})
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    public void deveListarTodosOsProdutos() throws Exception {
        ProdutoDTO produto1 = new ProdutoDTO();
        produto1.setId(1L);
        produto1.setNome("Dipirona");
        produto1.setQuantidade(10);
        produto1.setPrecoUnitario(new BigDecimal("2.50"));
        produto1.setValorTotal(new BigDecimal("25.00"));

        ProdutoDTO produto2 = new ProdutoDTO();
        produto2.setId(2L);
        produto2.setNome("Paracetamol");
        produto2.setQuantidade(5);
        produto2.setPrecoUnitario(new BigDecimal("3.00"));
        produto2.setValorTotal(new BigDecimal("15.00"));

        when(produtoService.listarTodos()).thenReturn(Arrays.asList(produto1, produto2));

        mockMvc.perform(get("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Dipirona"))
                .andExpect(jsonPath("$[1].nome").value("Paracetamol"));
    }
}
