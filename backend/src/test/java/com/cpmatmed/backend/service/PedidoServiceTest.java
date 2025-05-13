package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.PedidoRequest;
import com.cpmatmed.backend.dto.PedidoRequest.ItemPedidoRequest;
import com.cpmatmed.backend.dto.PedidoResponse;
import com.cpmatmed.backend.exception.ResourceNotFoundException;
import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.ItemPedido;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.cpmatmed.backend.service.impl.PedidoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private CompradorRepository compradorRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    private PedidoRequest pedidoRequest;

    @Before
    public void setUp() {
        pedidoRequest = new PedidoRequest();
        pedidoRequest.setCompradorId(1L);
        pedidoRequest.setFornecedorId(1L);

        ItemPedidoRequest item1 = new ItemPedidoRequest();
        item1.setProdutoId(1L);
        item1.setQuantidade(2);  // Adicionado quantidade

        ItemPedidoRequest item2 = new ItemPedidoRequest();
        item2.setProdutoId(2L);
        item2.setQuantidade(3);  // Adicionado quantidade

        pedidoRequest.setItens(Arrays.asList(item1, item2)); // Usando o novo nome do campo
      
    }

    @Test
    public void deveSalvarPedidoComSucesso() {
        // Configuração dos mocks
        Comprador comprador = new Comprador();
        comprador.setId(1L);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);

        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setPrecoUnitario(BigDecimal.valueOf(100));

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setPrecoUnitario(BigDecimal.valueOf(100));

        Pedido pedidoSalvo = new Pedido();
        pedidoSalvo.setId(1L);
        pedidoSalvo.setComprador(comprador);
        pedidoSalvo.setFornecedor(fornecedor);

        // Criação dos itens do pedido
        ItemPedido item1 = new ItemPedido();
        item1.setProduto(produto1);
        item1.setQuantidade(2);
        item1.setPedido(pedidoSalvo);

        ItemPedido item2 = new ItemPedido();
        item2.setProduto(produto2);
        item2.setQuantidade(3);
        item2.setPedido(pedidoSalvo);

        pedidoSalvo.getItens().addAll(Arrays.asList(item1, item2));

        // Configuração dos whens
        when(compradorRepository.findById(1L)).thenReturn(Optional.of(comprador));
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto1));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoSalvo);

        // Execução do teste
        PedidoResponse resultado = pedidoService.salvarPedido(pedidoRequest);

        // Verificações
        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getId());
        assertEquals(new BigDecimal("500.00"), resultado.getValorTotal());  // (2*100) + (3*100) = 500
    }

    // Os outros testes permanecem iguais...
    @Test(expected = ResourceNotFoundException.class)
    public void deveLancarErroQuandoCompradorNaoEncontrado() {
        when(compradorRepository.findById(1L)).thenReturn(Optional.empty());
        pedidoService.salvarPedido(pedidoRequest);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deveLancarErroQuandoFornecedorNaoEncontrado() {
        Comprador comprador = new Comprador();
        comprador.setId(1L);

        when(compradorRepository.findById(1L)).thenReturn(Optional.of(comprador));
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.empty());

        pedidoService.salvarPedido(pedidoRequest);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deveLancarErroQuandoProdutoNaoEncontrado() {
        Comprador comprador = new Comprador();
        comprador.setId(1L);
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);

        when(compradorRepository.findById(1L)).thenReturn(Optional.of(comprador));
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        pedidoService.salvarPedido(pedidoRequest);
    }
}