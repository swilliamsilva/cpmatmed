
package com.cpmatmed.backend.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Produto;

@Component
public class ProdutoMapper {

	// Método estático para conversão de Produto para ProdutoDTO
    public static ProdutoDTO toDTO(Produto produto) {
        if (produto == null) return null;

        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setQuantidade(produto.getQuantidade());
        dto.setPrecoUnitario(produto.getPrecoUnitario());
        dto.setValorTotal(produto.getValorTotal());
        
       
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
        produto.setQuantidade(dto.getQuantidade());
        produto.setPrecoUnitario(dto.getPrecoUnitario());
        
     // Calcula valorTotal automaticamente
        BigDecimal valorTotal = dto.getPrecoUnitario().multiply(BigDecimal.valueOf(dto.getQuantidade()));
        return produto;
    }
}
