package com.cpmatmed.backend.dto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valorTotal;

    // Getters e Setters
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal d) {
        this.precoUnitario = d;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal d) {
        this.valorTotal = d;
    }
}
