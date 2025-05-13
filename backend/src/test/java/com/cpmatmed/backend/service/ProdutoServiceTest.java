package com.cpmatmed.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.cpmatmed.backend.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProdutoServiceTest {

    private ProdutoRepository produtoRepository;
    private ProdutoServiceImpl produtoService;

    @BeforeEach
    void setUp() throws Exception {
        produtoRepository = mock(ProdutoRepository.class);
        produtoService = new ProdutoServiceImpl(produtoRepository);
        
    }

    @Test
    void listarTodosProdutos_DeveRetornarProdutoDTO() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição teste");
        produto.setPrecoUnitario(BigDecimal.valueOf(15));

        when(produtoRepository.findAll()).thenReturn(Collections.singletonList(produto));

        List<ProdutoDTO> resultado = produtoService.listarTodosProdutos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        ProdutoDTO dto = resultado.get(0);
        assertEquals("Produto Teste", dto.getNome());
        assertEquals("Descrição teste", dto.getDescricao());
        assertEquals(BigDecimal.valueOf(15), dto.getPrecoUnitario()); // Ajustado
    }
}