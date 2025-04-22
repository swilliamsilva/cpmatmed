package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.*;

import java.util.List;

public interface PedidoService { // Nome exato da interface
    PedidoResponse salvarPedido(PedidoRequest pedidoRequest);
    List<PedidoResponse> listarPedidos(); // Nome do método igual na implementação
    PedidoResponse buscarPedidoPorId(Long id);
    void deletarPedido(Long id);
}