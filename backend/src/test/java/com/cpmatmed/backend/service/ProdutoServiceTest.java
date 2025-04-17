package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.mapper.ProdutoMapper;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    private ProdutoRepository produtoRepository;
    private ProdutoMapper produtoMapper;
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoRepository.class);
        produtoMapper = new ProdutoMapper(); // instância real
        produtoService = new ProdutoService(produtoRepository, produtoMapper);
    }

    @Test
    void deveListarTodosProdutos() {
        Produto produto1 = new Produto(1L, "Dipirona", 10, new BigDecimal("2.50"));
        Produto produto2 = new Produto(2L, "Paracetamol", 5, new BigDecimal("3.00"));

        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        List<ProdutoDTO> result = produtoService.listarTodos();

        assertEquals(2, result.size());
        assertEquals("Dipirona", result.get(0).getNome());
        assertEquals("Paracetamol", result.get(1).getNome());

        verify(produtoRepository, times(1)).findAll();
    }
}
