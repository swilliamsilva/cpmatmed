package com.cpmatmed.backend;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;

public class PedidoProdutoTest {

    private Pedido pedido;
    private Produto produto1;
    private Produto produto2;

    @Before
    public void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);

        produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto A");
        produto1.setPrecoUnitario(BigDecimal.valueOf(10.0)); // CORRETO
        produto1.setQuantidade(2);
        produto1.setPedido(pedido);

        produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto B");
        produto2.setPrecoUnitario(BigDecimal.valueOf(5.0)); // CORRETO
        produto2.setQuantidade(3);
        produto2.setPedido(pedido);

        pedido.setProdutos(Arrays.asList(produto1, produto2));
    }

    @Test
    public void testTotalProdutos() {
        int totalEsperado = 2 + 3;
        assertEquals(totalEsperado, pedido.getTotalProdutos().intValue());
    }

    @Test
    public void testValorTotalDoPedido() {
        BigDecimal esperado = BigDecimal.valueOf(2 * 10.0 + 3 * 5.0);
        assertEquals(0, esperado.compareTo(pedido.getValorTotal()));
    }

    @Test
    public void testAdicionarProdutosAoPedido() {
        assertEquals(2, pedido.getProdutos().size());
        assertEquals("Produto A", pedido.getProdutos().get(0).getNome());
        assertEquals("Produto B", pedido.getProdutos().get(1).getNome());
    }
}
