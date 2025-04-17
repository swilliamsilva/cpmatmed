package com.cpmatmed.backend.mapper;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Produto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setQuantidade(produto.getQuantidade());
        dto.setPrecoUnitario(produto.getPrecoUnitario());
        dto.setValorTotal(produto.getValorTotal()); // calculado no getter da entidade
        return dto;
    }

    public Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setQuantidade(dto.getQuantidade());
        produto.setPrecoUnitario(dto.getPrecoUnitario());
        // valorTotal será calculado automaticamente via getValorTotal()
        return produto;
    }

    public List<ProdutoDTO> toProdutoDTOs(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Produto> toProdutoEntities(List<ProdutoDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
