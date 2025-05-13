package com.cpmatmed.backend;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import com.cpmatmed.backend.model.ItemPedido;
import org.junit.Before;
import org.junit.Test;

import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;

public class PedidoProdutoTest {

    private Pedido pedido;
    private Produto produto1;
    private Produto produto2;
    private ItemPedido item1;
    private ItemPedido item2;

    @Before
    public void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);

        produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto A");
        produto1.setPrecoUnitario(BigDecimal.valueOf(10.0));

        produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto B");
        produto2.setPrecoUnitario(BigDecimal.valueOf(5.0));

        // Cria itens do pedido com quantidades
        item1 = new ItemPedido();
        item1.setProduto(produto1);
        item1.setQuantidade(2);
        item1.setPedido(pedido);

        item2 = new ItemPedido();
        item2.setProduto(produto2);
        item2.setQuantidade(3);
        item2.setPedido(pedido);

        pedido.getItens().addAll(Arrays.asList(item1, item2));
    }

    @Test
    public void testValorTotalDoPedido() {
        BigDecimal esperado = BigDecimal.valueOf(2 * 10.0 + 3 * 5.0);
        assertEquals(0, esperado.compareTo(pedido.getValorTotal()));
    }

    @Test
    public void testAdicionarProdutosAoPedido() {
        assertEquals(2, pedido.getItens().size());
        assertEquals("Produto A", pedido.getItens().get(0).getProduto().getNome());
        assertEquals("Produto B", pedido.getItens().get(1).getProduto().getNome());
    }
}