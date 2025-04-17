package com.cpmatmed.backend.mapper;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    private static final String COMPRADOR_DESCONHECIDO = "Desconhecido";
    private static final String FORNECEDOR_DESCONHECIDO = "Desconhecido";

    private final CompradorRepository compradorRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public PedidoMapper(CompradorRepository compradorRepository,
                        FornecedorRepository fornecedorRepository,
                        ProdutoRepository produtoRepository,
                        ProdutoMapper produtoMapper) {
        this.compradorRepository = compradorRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    // Entidade -> DTO
    public List<PedidoDTO> toPedidoDTOs(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toPedidoDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO toPedidoDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setNomeComprador(getNomeComprador(pedido.getComprador()));
        dto.setNomeFornecedor(getNomeFornecedor(pedido.getFornecedor()));
        dto.setTotalProdutos(pedido.getTotalProdutos());
        dto.setValorTotal(pedido.getValorTotal());

        List<ProdutoDTO> produtos = pedido.getProdutos().stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());

        dto.setProdutos(produtos);
        return dto;
    }

    // DTO -> Entidade
    public Pedido toPedido(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setId(dto.getId());

        Comprador comprador = compradorRepository.findById(dto.getCompradorId()).orElse(null);
        if (comprador == null) {
            throw new RuntimeException("Comprador não encontrado: " + dto.getCompradorId());
        }
        pedido.setComprador(comprador);

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId()).orElse(null);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado: " + dto.getFornecedorId());
        }
        pedido.setFornecedor(fornecedor);

        if (dto.getProdutos() != null) {
            List<Produto> produtos = dto.getProdutos().stream().map(produtoDTO -> {
                Produto produto = produtoRepository.findById(produtoDTO.getId()).orElse(null);
                if (produto == null) {
                    throw new RuntimeException("Produto não encontrado: " + produtoDTO.getId());
                }
                produto.setQuantidade(produtoDTO.getQuantidade());
                produto.setPedido(pedido);
                return produto;
            }).collect(Collectors.toList());

            pedido.setProdutos(produtos);
        }

        return pedido;
    }

    // Converter lista de produtos para lista de DTOs
    public List<ProdutoDTO> toProdutoDTOs(List<Produto> produtos) {
        return produtos.stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Auxiliares
    private static String getNomeComprador(Comprador comprador) {
        return comprador != null && comprador.getNome() != null
                ? comprador.getNome()
                : COMPRADOR_DESCONHECIDO;
    }

    private static String getNomeFornecedor(Fornecedor fornecedor) {
        return fornecedor != null && fornecedor.getNome() != null
                ? fornecedor.getNome()
                : FORNECEDOR_DESCONHECIDO;
    }
}
