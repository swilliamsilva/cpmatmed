package com.cpmatmed.backend.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.cpmatmed.backend.dto.PedidoRequest;
import com.cpmatmed.backend.dto.PedidoRequest.ItemPedidoRequest;
import com.cpmatmed.backend.dto.PedidoResponse;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;

public class PedidoMapper {

    // Converte Pedido (entidade) em PedidoResponse (DTO para frontend)
    public static PedidoResponse toResponse(Pedido pedido) {
        if (pedido == null) return null;

        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());

        if (pedido.getComprador() != null) {
            response.setNomeComprador(pedido.getComprador().getNome());
        }

        if (pedido.getFornecedor() != null) {
            response.setNomeFornecedor(pedido.getFornecedor().getNome());
        }

        List<ProdutoDTO> produtosDTO = pedido.getProdutos().stream()
                .map(ProdutoMapper::toDTO)
                .collect(Collectors.toList());

        response.setProdutos(produtosDTO);

        int totalItens = produtosDTO.stream()
                .mapToInt(ProdutoDTO::getQuantidade)
                .sum();
        response.setTotalProdutos(totalItens);

        BigDecimal valorTotal = produtosDTO.stream()
                .map(ProdutoDTO::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        response.setValorTotal(valorTotal);

        return response;
    }

    // Converte PedidoRequest (DTO recebido do frontend) em Pedido (entidade)
    public static Pedido toEntity(PedidoRequest request, Comprador comprador, Fornecedor fornecedor, List<Produto> produtos) {
        if (request == null) return null;

        Pedido pedido = new Pedido();
        pedido.setComprador(comprador);
        pedido.setFornecedor(fornecedor);

        // Vincula cada produto ao pedido
        for (Produto produto : produtos) {
            produto.setPedido(pedido);
        }

        pedido.setProdutos(produtos);
        return pedido;
    }

    // Converte lista de ItemPedidoRequest para entidades Produto (utilizado no serviço)
    public static List<Produto> mapProdutosFromRequest(List<ItemPedidoRequest> itens) {
        if (itens == null) return null;

        return itens.stream().map(item -> {
            Produto produto = new Produto();
            produto.setId(item.getProdutoId());
            produto.setQuantidade(item.getQuantidade());
            return produto;
        }).collect(Collectors.toList());
    }
}
