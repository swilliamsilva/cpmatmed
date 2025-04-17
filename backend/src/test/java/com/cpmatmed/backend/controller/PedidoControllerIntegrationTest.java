package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Comprador comprador;
    private Fornecedor fornecedor;

    @BeforeEach
    void setup() {
        produtoRepository.deleteAll();
        pedidoRepository.deleteAll();
        fornecedorRepository.deleteAll();
        compradorRepository.deleteAll();

        comprador = compradorRepository.save(new Comprador("Joana"));
        fornecedor = fornecedorRepository.save(new Fornecedor("Distribuidora X"));
    }

    @Test
    void deveBuscarPedidoPorId() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);
        pedido = pedidoRepository.save(pedido);

        mockMvc.perform(get("/pedidos/" + pedido.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pedido.getId()));
    }

    @Test
    void deveCriarNovoPedido() throws Exception {
        // Cria e salva o produto
        Produto produto = new Produto(
            "Dipirona", 
            5, 
            new BigDecimal("3.00"), 
            null
        );
        produto = produtoRepository.save(produto);

        // Cria o DTO do produto corretamente
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setPrecoUnitario(produto.getPrecoUnitario()); // Mantém como BigDecimal
        produtoDTO.setQuantidade(5);
        
        // Calcula o valor total com BigDecimal
        produtoDTO.setValorTotal(
            produtoDTO.getPrecoUnitario().multiply(
                BigDecimal.valueOf(produtoDTO.getQuantidade())
            )
        );

        // Cria o DTO do pedido
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setCompradorId(comprador.getId());
        pedidoDTO.setFornecedorId(fornecedor.getId());
        pedidoDTO.setProdutos(Collections.singletonList(produtoDTO));

        // Executa e valida a requisição
        mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedidoDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists());
    }
}