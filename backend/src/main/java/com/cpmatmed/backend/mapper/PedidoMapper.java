package com.cpmatmed.backend.mapper;

import com.cpmatmed.backend.dto.PedidoResponse;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.ItemPedido;
import com.cpmatmed.backend.model.Pedido;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {
    public static PedidoResponse toResponse(Pedido pedido) {
        if (pedido == null) return null;
    
        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());
    
        // Preenche nomes
        if (pedido.getComprador() != null) {
            response.setNomeComprador(pedido.getComprador().getNome());
        }
        if (pedido.getFornecedor() != null) {
            response.setNomeFornecedor(pedido.getFornecedor().getNome());
        }
    
        // Mapeia produtos
        List<ProdutoDTO> produtos = pedido.getItens().stream()
                .map(item -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setId(item.getProduto().getId());
                    dto.setNome(item.getProduto().getNome());
                    dto.setDescricao(item.getProduto().getDescricao());
                    dto.setQuantidade(item.getQuantidade());
                    dto.setPrecoUnitario(item.getProduto().getPrecoUnitario());
                    dto.setValorTotal(item.getProduto().getPrecoUnitario()
                            .multiply(BigDecimal.valueOf(item.getQuantidade())));
                    return dto;
                })
                .collect(Collectors.toList());
    
        response.setProdutos(produtos);
        response.setTotalProdutos(produtos.size());
        response.setValorTotal(pedido.getValorTotal());
    
        return response;
    }
   
}