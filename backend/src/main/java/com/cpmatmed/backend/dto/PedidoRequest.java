package com.cpmatmed.backend.dto;

import java.util.List;

public class PedidoRequest {

    private Long compradorId;
    private Long fornecedorId;
    private List<ItemPedidoRequest> itens; // Campo renomeado para "itens"

    // Getters e Setters atualizados
    public Long getCompradorId() { return compradorId; }
    public void setCompradorId(Long compradorId) { this.compradorId = compradorId; }
    public Long getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }
    public List<ItemPedidoRequest> getItens() { return itens; } // Getter renomeado
    public void setItens(List<ItemPedidoRequest> itens) { this.itens = itens; } // Setter renomeado

    public static class ItemPedidoRequest {
        private Long produtoId;
        private Integer quantidade;

        // Getters e Setters mantidos
        public Long getProdutoId() { return produtoId; }
        public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    }
}