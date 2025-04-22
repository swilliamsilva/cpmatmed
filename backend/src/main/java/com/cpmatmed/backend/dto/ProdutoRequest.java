package com.cpmatmed.backend.dto;

import java.math.BigDecimal;

public class ProdutoRequest {

    private String nome;
    private int quantidade;
    private BigDecimal precoUnitario;
    private Long fornecedorId;

    public ProdutoRequest() {}

    public ProdutoRequest(String nome, int quantidade, BigDecimal precoUnitario, Long fornecedorId) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.fornecedorId = fornecedorId;
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

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }
}
