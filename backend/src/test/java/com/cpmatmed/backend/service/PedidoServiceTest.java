package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.exception.PedidoNaoEncontradoException;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.service.PedidoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) // Habilita o Mockito com JUnit 4
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    // Remove métodos duplicados de setup
    @Before
    public void init() {
        // Configurações comuns (se necessárias)
    }

    @Test
    public void testListarProdutosDoPedido_DeveRetornarListaDeProdutosDTO() {
        // Configuração do mock
        Produto produto1 = new Produto();
        produto1.setNome("Dipirona");
        produto1.setPrecoUnitario(new BigDecimal("3.50"));
        produto1.setQuantidade(2);

        Produto produto2 = new Produto();
        produto2.setNome("Paracetamol");
        produto2.setPrecoUnitario(new BigDecimal("5.00"));
        produto2.setQuantidade(1);

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setProdutos(Arrays.asList(produto1, produto2));

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Execução do teste
        List<ProdutoDTO> resultado = pedidoService.listarProdutosDoPedido(1L);

        // Verificações
        assertEquals(2, resultado.size());
        assertEquals("Dipirona", resultado.get(0).getNome());
        assertEquals("Paracetamol", resultado.get(1).getNome());
    }

    @Test(expected = PedidoNaoEncontradoException.class) // Testa exceção específica
    public void testListarProdutosDoPedido_DeveLancarExcecao_QuandoPedidoNaoEncontrado() {
        // Configuração do mock
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        // Execução do teste (deve lançar exceção)
        pedidoService.listarProdutosDoPedido(99L);
    }
}