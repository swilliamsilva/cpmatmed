package com.cpmatmed.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;

public class PedidoMapper {

    public static PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) return null;

        List<ProdutoDTO> produtosDTO = pedido.getProdutos().stream()
                .map(ProdutoMapper::toDTO)
                .collect(Collectors.toList());

        return new PedidoDTO(
                pedido.getId(),
                pedido.getComprador().getId(),
                pedido.getFornecedor().getId(),
                pedido.getComprador().getNome(),
                pedido.getFornecedor().getNome(),
                produtosDTO,
                pedido.getTotalProdutos(),
                pedido.getValorTotal()
        );
    }

    public static Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;

        Pedido pedido = new Pedido();
        pedido.setId(dto.getId());
        pedido.setComprador(CompradorMapper.fromIdAndNome(dto.getCompradorId(), dto.getNomeComprador()));
        pedido.setFornecedor(FornecedorMapper.fromIdAndNome(dto.getFornecedorId(), dto.getNomeFornecedor()));

        List<Produto> produtos = dto.getProdutos().stream()
                .map(ProdutoMapper::toEntity)
                .collect(Collectors.toList());
        pedido.setProdutos(produtos);
        return pedido;
    }
}
