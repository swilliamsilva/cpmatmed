package com.cpmatmed.backend;

import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PedidoProdutoTests {

    @LocalServerPort
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

        try {
            List<Comprador> compradores = compradorRepository.findAll();
            List<Fornecedor> fornecedores = fornecedorRepository.findAll();
            List<Produto> produtos = produtoRepository.findAll();

            if (compradores.isEmpty() || fornecedores.isEmpty() || produtos.isEmpty()) {
                fail("⚠️ Banco de dados de teste está vazio. Verifique se há registros de Comprador, Fornecedor e Produto.");
                return;
            }

            Long compradorId = compradores.get(0).getId();
            Long fornecedorId = fornecedores.get(0).getId();
            Long produtoId = produtos.get(0).getId();

            Map<String, Object> produto1 = new HashMap<>();
            produto1.put("id", produtoId);
            produto1.put("nome", "Paracetamol");
            produto1.put("quantidade", 2);
            produto1.put("precoUnitario", BigDecimal.valueOf(10.0));
            produto1.put("valorTotal", BigDecimal.valueOf(20.0));

            Map<String, Object> pedidoDTO = new HashMap<>();
            pedidoDTO.put("compradorId", compradorId);
            pedidoDTO.put("fornecedorId", fornecedorId);
            pedidoDTO.put("produtos", Collections.singletonList(produto1));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(pedidoDTO), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/pedidos", request, String.class);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        } catch (CannotGetJdbcConnectionException | JDBCConnectionException e) {
            fail("❌ Não foi possível conectar ao banco de dados. Verifique se o serviço está ativo.\nErro: " + e.getMessage());
        } catch (DataAccessException e) {
            fail("❌ Erro ao acessar dados no banco. Verifique integridade da base e se as tabelas foram criadas.\nErro: " + e.getMessage());
        } catch (Exception e) {
            fail("❌ Erro inesperado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
