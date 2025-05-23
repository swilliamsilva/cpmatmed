package com.cpmatmed.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.cpmatmed.backend.dto.PedidoRequest;
import com.cpmatmed.backend.dto.PedidoResponse;
import com.cpmatmed.backend.exception.ResourceNotFoundException;
import com.cpmatmed.backend.mapper.PedidoMapper;
import com.cpmatmed.backend.model.*;
import com.cpmatmed.backend.repository.*;
import com.cpmatmed.backend.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CompradorRepository compradorRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            CompradorRepository compradorRepository,
            FornecedorRepository fornecedorRepository,
            ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.compradorRepository = compradorRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public PedidoResponse salvarPedido(PedidoRequest pedidoRequest) {
        Comprador comprador = compradorRepository.findById(pedidoRequest.getCompradorId())
                .orElseThrow(() -> new ResourceNotFoundException("Comprador não encontrado"));
        Fornecedor fornecedor = fornecedorRepository.findById(pedidoRequest.getFornecedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);

        // ↓↓↓↓ CORREÇÃO AQUI: getProdutos() → getItens() ↓↓↓↓
        for (PedidoRequest.ItemPedidoRequest item : pedidoRequest.getItens()) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + item.getProdutoId()));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(item.getQuantidade());
            pedido.getItens().add(itemPedido);
        }

        Pedido salvo = pedidoRepository.save(pedido);
        return PedidoMapper.toResponse(salvo);
    }

    @Override
    public List<PedidoResponse> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponse buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        return PedidoMapper.toResponse(pedido);
    }

    @Override
    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}