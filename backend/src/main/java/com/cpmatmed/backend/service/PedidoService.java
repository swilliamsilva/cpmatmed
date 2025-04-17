package com.cpmatmed.backend.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.exception.EntidadeNaoEncontradaException;
import com.cpmatmed.backend.exception.PedidoNaoEncontradoException;
import com.cpmatmed.backend.exception.ValidacaoException;
import com.cpmatmed.backend.mapper.PedidoMapper;
import com.cpmatmed.backend.mapper.ProdutoMapper;
import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.PedidoRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CompradorRepository compradorRepository;
    private final FornecedorRepository fornecedorRepository;
    private final PedidoMapper pedidoMapper;
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Autowired
    public PedidoService(
        PedidoRepository pedidoRepository,
        CompradorRepository compradorRepository,
        FornecedorRepository fornecedorRepository,
        PedidoMapper pedidoMapper,
        ProdutoRepository produtoRepository,
        ProdutoMapper produtoMapper
    ) {
        this.pedidoRepository = pedidoRepository;
        this.compradorRepository = compradorRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.pedidoMapper = pedidoMapper;
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> listarTodosPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toPedidoDTOs(pedidos);
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarProdutosDoPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));

        if (pedido.getProdutos() == null || pedido.getProdutos().isEmpty()) {
            return Collections.emptyList();
        }

        return pedidoMapper.toProdutoDTOs(pedido.getProdutos());
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarTodosProdutos() {
        return produtoMapper.toProdutoDTOs(produtoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PedidoDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
        return pedidoMapper.toPedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        validarPedidoDTO(pedidoDTO);

        Comprador comprador = compradorRepository.findById(pedidoDTO.getCompradorId())
                .orElseThrow(() -> new ValidacaoException("Comprador não encontrado com ID: " + pedidoDTO.getCompradorId()));

        Fornecedor fornecedor = fornecedorRepository.findById(pedidoDTO.getFornecedorId())
                .orElseThrow(() -> new ValidacaoException("Fornecedor não encontrado com ID: " + pedidoDTO.getFornecedorId()));

        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);

        if (pedidoDTO.getProdutos() != null && !pedidoDTO.getProdutos().isEmpty()) {
            List<Produto> produtos = pedidoDTO.getProdutos().stream()
                    .map(produtoDTO -> {
                        Produto produto = new Produto();
                        produto.setNome(produtoDTO.getNome());
                        produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
                        produto.setQuantidade(produtoDTO.getQuantidade());
                        produto.setPedido(pedido);
                        return produto;
                    }).collect(Collectors.toList());
            pedido.setProdutos(produtos);
        }

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return pedidoMapper.toPedidoDTO(pedidoSalvo);
    }

    private void validarPedidoDTO(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getCompradorId() == null) {
            throw new ValidacaoException("ID do comprador é obrigatório");
        }
        if (pedidoDTO.getFornecedorId() == null) {
            throw new ValidacaoException("ID do fornecedor é obrigatório");
        }
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado"));
    }
}
