package com.cpmatmed.backend.dto;

import java.math.BigDecimal;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valorTotal;
    private String nomeFornecedor;

    public ProdutoResponse() {}

    public ProdutoResponse(Long id, String nome, int quantidade, BigDecimal precoUnitario,
                           BigDecimal valorTotal, String nomeFornecedor) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
        this.nomeFornecedor = nomeFornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
}
