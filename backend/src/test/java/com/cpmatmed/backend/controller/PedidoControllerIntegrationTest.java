package com.cpmatmed.backend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Arrays;

import com.cpmatmed.backend.CpmatmedBackendApplication;
import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.repository.PedidoRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CpmatmedBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Before
    public void setup() {
        pedidoRepository.deleteAll();
    }

    @Test
    public void testCriarEPegarPedido() {
        // Criar DTO de produto
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Teste");
        produtoDTO.setPrecoUnitario(new BigDecimal("20.0")); // ✅ corrigido
        produtoDTO.setQuantidade(2);

        // Criar DTO de pedido com o produto
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setDescricao("Pedido de Integração");
        pedidoDTO.setProdutos(Arrays.asList(produtoDTO));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PedidoDTO> request = new HttpEntity<>(pedidoDTO, headers);

        // POST: criar pedido
        ResponseEntity<PedidoDTO> response = restTemplate.postForEntity("/api/pedidos", request, PedidoDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());

        // GET: listar pedidos
        ResponseEntity<PedidoDTO[]> getResponse = restTemplate.getForEntity("/api/pedidos", PedidoDTO[].class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        PedidoDTO[] pedidos = getResponse.getBody();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.length);
        assertEquals("Pedido de Integração", pedidos[0].getDescricao());
        assertEquals(1, pedidos[0].getProdutos().size());
        assertEquals("Produto Teste", pedidos[0].getProdutos().get(0).getNome());
    }

    @Test
    public void testBancoInalcançável() {
        try {
            pedidoRepository.findAll(); // Verifica conexão com o banco
        } catch (Exception ex) {
            fail("Erro ao conectar com o banco de dados: " + ex.getMessage());
        }
    }
}
