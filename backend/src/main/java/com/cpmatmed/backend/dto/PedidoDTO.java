package com.cpmatmed.backend.dto;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private String nomeComprador;
    private String nomeFornecedor;
    private Integer totalProdutos;
    private double valorTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Integer getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(Integer totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    public String getNome() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getNome'");
    }

    public int getQuantidade() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getQuantidade'");
    }
}
