package com.cpmatmed.backend;

import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PedidoProdutoTests {

    
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api";
    }

    @Test
    void deveCriarPedidoComSucesso() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Obtendo IDs de comprador, fornecedor e produto
        Long compradorId = compradorRepository.findAll().get(0).getId();
        Long fornecedorId = fornecedorRepository.findAll().get(0).getId();
        Long produtoId = produtoRepository.findAll().get(0).getId();

        // Criando produto com BigDecimal para valores monetários
        Map<String, Object> produto1 = new HashMap<>();
        produto1.put("id", produtoId);
        produto1.put("nome", "Paracetamol");
        produto1.put("quantidade", 2);
        produto1.put("precoUnitario", BigDecimal.valueOf(10.0));  // Usando BigDecimal
        produto1.put("valorTotal", BigDecimal.valueOf(20.0));     // Usando BigDecimal

        // Criando DTO do pedido
        Map<String, Object> pedidoDTO = new HashMap<>();
        pedidoDTO.put("compradorId", compradorId);
        pedidoDTO.put("fornecedorId", fornecedorId);
        pedidoDTO.put("produtos", Collections.singletonList(produto1)); // Ajuste de sintaxe para Java 8

        // Configurando cabeçalhos para requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Enviando a requisição POST
        HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(pedidoDTO), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/pedidos", request, String.class);

        // Verificando se a resposta é o código 201 (Created)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
