package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")  // Permite o CORS de qualquer origem
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Endpoint para listar pedidos
    @GetMapping
    public List<PedidoDTO> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    // Endpoint para listar produtos por pedido
    @GetMapping("/{id}/produtos")
    public List<ProdutoDTO> listarProdutosPorPedido(@PathVariable Long id) {
        return pedidoService.listarProdutosPorPedido(id);
    }

    // Endpoint para criar um novo pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }
}
