package com.cpmatmed.backend.mapper;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Produto;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    // Converts Produto entity to ProdutoDTO
    public static ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPrecoUnitario(produto.getPrecoUnitario());
        return dto;
    }

    // Converts ProdutoDTO to Produto entity
    public static Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId()); // Include this if updates are allowed
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPrecoUnitario(dto.getPrecoUnitario());
        return produto;
    }

    // Converts a list of entities to DTOs
    public static List<ProdutoDTO> toDTOList(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoMapper::toDTO)
                .collect(Collectors.toList());
    }
}