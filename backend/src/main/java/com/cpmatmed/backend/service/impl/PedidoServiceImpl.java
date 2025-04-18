package com.cpmatmed.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.mapper.PedidoMapper;
import com.cpmatmed.backend.mapper.ProdutoMapper;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<PedidoDTO> listarTodosPedidos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = PedidoMapper.toEntity(pedidoDTO);
        Pedido salvo = pedidoRepository.save(pedido);
        return PedidoMapper.toDTO(salvo);
    }

    @Override
    public List<ProdutoDTO> listarProdutosDoPedido(Long idPedido) {
        return pedidoRepository.findById(idPedido)
                .map(pedido -> pedido.getProdutos().stream()
                        .map(ProdutoMapper::toDTO)
                        .collect(Collectors.toList()))
                .orElse(null);
    }
}
