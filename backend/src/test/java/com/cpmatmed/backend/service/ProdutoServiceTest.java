package com.cpmatmed.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.mapper.ProdutoMapper;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.cpmatmed.backend.service.impl.ProdutoServiceImpl;

class ProdutoServiceTest {

    private ProdutoRepository produtoRepository;
    private ProdutoMapper produtoMapper;
    private ProdutoServiceImpl produtoService; // Alterado para ProdutoServiceImpl

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoRepository.class);
        produtoMapper = new ProdutoMapper();
        // Corrigido: Usando o construtor correto (adicione o construtor em ProdutoServiceImpl)
        produtoService = new ProdutoServiceImpl(produtoRepository, produtoMapper);
    }

    @Test
    void listarTodos() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setQuantidade(10);
        produto.setPrecoUnitario(BigDecimal.valueOf(15));
        
        // Corrigido: Calcula valorTotal usando os campos do produto
        BigDecimal valorTotal = produto.getPrecoUnitario().multiply(BigDecimal.valueOf(produto.getQuantidade()));
    //    produto.setValorTotal(valorTotal); // Garanta que Produto tem setValorTotal()

        when(produtoRepository.findAll()).thenReturn(Collections.singletonList(produto));

        List<ProdutoDTO> resultado = produtoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).getNome());
        assertEquals(BigDecimal.valueOf(150), resultado.get(0).getValorTotal()); // Verifica o valorTotal
    }
}