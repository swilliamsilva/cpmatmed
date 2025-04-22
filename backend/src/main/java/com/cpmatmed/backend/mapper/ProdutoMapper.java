package com.cpmatmed.backend.mapper;

import java.math.BigDecimal;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Produto;

public class ProdutoMapper {

    public static ProdutoDTO toDTO(Produto produto) {
        if (produto == null) return null;

        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao()); // <-- Aqui!
        dto.setQuantidade(produto.getQuantidade());
        dto.setPrecoUnitario(produto.getPrecoUnitario());

        if (produto.getPrecoUnitario() != null && produto.getQuantidade() != null) {
            BigDecimal valorTotal = produto.getPrecoUnitario()
                    .multiply(BigDecimal.valueOf(produto.getQuantidade()));
            dto.setValorTotal(valorTotal);
        }

        if (produto.getPedido() != null && produto.getPedido().getFornecedor() != null) {
            dto.setFornecedorId(produto.getPedido().getFornecedor().getId());
        }

        return dto;
    }

    public static Produto toEntity(ProdutoDTO dto) {
        if (dto == null) return null;

        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao()); // <-- Aqui também!
        produto.setQuantidade(dto.getQuantidade());
        produto.setPrecoUnitario(dto.getPrecoUnitario());
        return produto;
    }
}
