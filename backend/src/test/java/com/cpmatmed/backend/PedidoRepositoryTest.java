package com.cpmatmed.backend;

import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestCpmatmedBackendApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PedidoRepositoryTest {

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
    void init() {
        produtoRepository.deleteAll();
        pedidoRepository.deleteAll();
        compradorRepository.deleteAll();
        fornecedorRepository.deleteAll();

        comprador = compradorRepository.save(new Comprador(null, "Teste Comprador"));
        fornecedor = fornecedorRepository.save(new Fornecedor(null, "Teste Fornecedor"));
    }

    @Test
    void deveSalvarEPesquisarPedido() {
        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);

        Pedido salvo = pedidoRepository.save(pedido);
        assertNotNull(salvo.getId());

        Pedido encontrado = pedidoRepository.findById(salvo.getId()).orElse(null);
        assertNotNull(encontrado);
        assertEquals("Teste Comprador", encontrado.getComprador().getNome());
    }
}
