package com.cpmatmed.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = TestCpmatmedBackendApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setup() {
        produtoRepository.deleteAll();
        pedidoRepository.deleteAll();
        compradorRepository.deleteAll();
        fornecedorRepository.deleteAll();

        Comprador comprador = compradorRepository.save(new Comprador(null, "João da Silva"));
        Fornecedor fornecedor = fornecedorRepository.save(new Fornecedor(null, "Farmácia Vida"));

        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);

        Produto p1 = new Produto(null, "Dipirona", 3, 5.0, pedido);
        Produto p2 = new Produto(null, "Paracetamol", 2, 10.0, pedido);

        pedido.setProdutos(List.of(p1, p2));
        pedidoRepository.save(pedido);
    }

    @Test
    void deveListarTodosPedidos() throws Exception {
        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nomeComprador").value("João da Silva"))
                .andExpect(jsonPath("$[0].totalProdutos").value(5))
                .andExpect(jsonPath("$[0].valorTotal").value(35.0));
    }

    @Test
    void deveListarProdutosDeUmPedido() throws Exception {
        Long idPedido = pedidoRepository.findAll().get(0).getId();

        mockMvc.perform(get("/api/pedidos/" + idPedido + "/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Dipirona"))
                .andExpect(jsonPath("$[0].quantidade").value(3))
                .andExpect(jsonPath("$[0].valorTotal").value(15.0));
    }

    @Test
    void deveRetornarNotFoundParaPedidoInexistente() throws Exception {
        mockMvc.perform(get("/api/pedidos/999/produtos"))
                .andExpect(status().isNotFound());
    }
}
