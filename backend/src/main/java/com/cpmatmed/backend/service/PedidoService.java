package com.cpmatmed.backend.service;

import java.util.List;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;

public interface PedidoService {

    List<PedidoDTO> listarTodosPedidos();

    PedidoDTO buscarPedidoPorId(Long id);

    List<ProdutoDTO> listarProdutosDoPedido(Long id);

    PedidoDTO criarPedido(PedidoDTO pedidoDTO);
}
