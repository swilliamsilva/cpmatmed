package com.cpmatmed.backend.dto;

import java.util.List;

public class PedidoRequest {

    private Long compradorId;
    private Long fornecedorId;
    private List<ItemPedidoRequest> produtos;

    public Long getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Long compradorId) {
        this.compradorId = compradorId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public List<ItemPedidoRequest> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemPedidoRequest> produtos) {
        this.produtos = produtos;
    }

    public static class ItemPedidoRequest {
        private Long produtoId;
        private Integer quantidade;

        public Long getProdutoId() {
            return produtoId;
        }

        public void setProdutoId(Long produtoId) {
            this.produtoId = produtoId;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
    }
}
