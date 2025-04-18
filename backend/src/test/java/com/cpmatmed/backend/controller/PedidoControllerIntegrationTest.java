package com.cpmatmed.backend.controller;

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

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

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
        // Criar ProdutoDTO
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Teste");
        produtoDTO.setPrecoUnitario(new BigDecimal("20.00"));
        produtoDTO.setQuantidade(2);

        // Criar PedidoDTO
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setDescricao("Pedido de Integração");
        pedidoDTO.setProdutos(Arrays.asList(produtoDTO));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PedidoDTO> request = new HttpEntity<>(pedidoDTO, headers);

        // POST: criar pedido
        ResponseEntity<PedidoDTO> postResponse = restTemplate.postForEntity("/pedidos", request, PedidoDTO.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull("Pedido retornado está nulo", postResponse.getBody());
        assertNotNull("ID do pedido está nulo", postResponse.getBody().getId());

        // GET: listar todos os pedidos
        ResponseEntity<PedidoDTO[]> getResponse = restTemplate.getForEntity("/pedidos", PedidoDTO[].class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        PedidoDTO[] pedidos = getResponse.getBody();
        assertNotNull("Lista de pedidos está nula", pedidos);
        assertEquals("Quantidade de pedidos não esperada", 1, pedidos.length);
        assertEquals("Descrição incorreta", "Pedido de Integração", pedidos[0].getDescricao());
        assertEquals("Quantidade de produtos no pedido não bate", 1, pedidos[0].getProdutos().size());
        assertEquals("Nome do produto incorreto", "Produto Teste", pedidos[0].getProdutos().get(0).getNome());
    }

    @Test
    public void testBancoInalcançável() {
        try {
            pedidoRepository.findAll();
        } catch (Exception ex) {
            fail("Erro ao conectar com o banco de dados: " + ex.getMessage());
        }
    }
}
