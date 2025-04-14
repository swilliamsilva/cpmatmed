package com.cpmatmed.backend;

import com.cpmatmed.backend.model.*;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.cpmatmed.backend.service.PedidoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CpmatmedBackendApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private Comprador comprador;
    private Fornecedor fornecedor;

    @BeforeEach
    void setup() {
        produtoRepository.deleteAll();
        pedidoRepository.deleteAll();
        compradorRepository.deleteAll();
        fornecedorRepository.deleteAll();

        comprador = compradorRepository.save(new Comprador(null, "Maria Souza"));
        fornecedor = fornecedorRepository.save(new Fornecedor(null, "DrogaMais"));
    }

    @Test
    void deveCriarPedidoComProdutos() {
        Produto p1 = new Produto(null, "Amoxicilina", 2, 10.0, null);
        Produto p2 = new Produto(null, "Ibuprofeno", 1, 15.0, null);

        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);
        pedido.setProdutos(List.of(p1, p2));

        Pedido salvo = pedidoService.criarPedido(pedido);

        assertNotNull(salvo.getId());
        assertEquals(2, salvo.getProdutos().size());
    }

    @Test
    void deveLancarExcecaoParaPedidoSemProdutos() {
        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);
        pedido.setProdutos(List.of());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.criarPedido(pedido);
        });

        assertEquals("Pedido deve conter ao menos um produto", ex.getMessage());
    }
}
