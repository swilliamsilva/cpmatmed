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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
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
        // Limpa todas as entidades relacionadas em ordem correta
        produtoRepository.deleteAll();
        pedidoRepository.deleteAll();
        compradorRepository.deleteAll();
        fornecedorRepository.deleteAll();

        // Cria e salva entidades usando construtores corretos
        comprador = compradorRepository.save(new Comprador("Comprador Teste"));
        fornecedor = fornecedorRepository.save(new Fornecedor("Fornecedor Teste"));
    }

    @Test
    void deveSalvarEPesquisarPedido() {
        // Criação do pedido com relacionamentos
        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);

        // Persistência
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        assertNotNull(pedidoSalvo.getId(), "ID do pedido não foi gerado");

        // Consulta
        Pedido pedidoEncontrado = pedidoRepository.findById(pedidoSalvo.getId())
                .orElse(null);

        // Verificações
        assertNotNull(pedidoEncontrado, "Pedido não encontrado no banco de dados");
        assertEquals(
            comprador.getNome(), 
            pedidoEncontrado.getComprador().getNome(), 
            "Nome do comprador divergente"
        );
        assertEquals(
            fornecedor.getNome(), 
            pedidoEncontrado.getFornecedor().getNome(), 
            "Nome do fornecedor divergente"
        );
    }
}