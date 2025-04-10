package com.cpmatmed.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cpmatmed.backend.dto.PedidoDTO;

import repository.CompradorRepository;
import repository.FornecedorRepository;
import repository.PedidoRepository;
import repository.ProdutoRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private com.cpmatmed.backend.model.Comprador comprador;
    private com.cpmatmed.backend.model.Fornecedor fornecedor;

    @BeforeEach
    void init() {
        produtoRepository.deleteAll();
        pedidoRepository.deleteAll();
        compradorRepository.deleteAll();
        fornecedorRepository.deleteAll();

        comprador = compradorRepository.save(new com.cpmatmed.backend.model.Comprador(null, "Teste Comprador"));
        fornecedor = fornecedorRepository.save(new com.cpmatmed.backend.model.Fornecedor(null, "Teste Fornecedor"));
    }

    @Test
    void deveSalvarEPesquisarPedido() {
        com.cpmatmed.backend.model.Pedido pedido = new com.cpmatmed.backend.model.Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);

        com.cpmatmed.backend.model.Pedido salvo = pedidoRepository.save(pedido);
        assertNotNull(((com.cpmatmed.backend.model.Pedido) salvo).getId());

        com.cpmatmed.backend.model.Pedido encontrado = pedidoRepository.findById((Long) salvo.getId()).orElse(null);
        assertNotNull(encontrado);
        assertEquals("Teste Comprador", encontrado.getComprador().getNome());
    }

	public static MockMvcRequestBuilders findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
