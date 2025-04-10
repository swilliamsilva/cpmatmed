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
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}/produtos")
    public List<ProdutoDTO> listarProdutosPorPedido(@PathVariable Long id) {
        return pedidoService.listarProdutosPorPedido(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }
}
