package com.cpmatmed.backend.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Comprador comprador;

    @ManyToOne
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();

    public Pedido() {}

    public Pedido(Long id, Comprador comprador, Fornecedor fornecedor, List<Produto> produtos) {
        this.id = id;
        this.comprador = comprador;
        this.fornecedor = fornecedor;
        this.setProdutos(produtos); // usa o setter para manter o relacionamento correto
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos.clear();
        if (produtos != null) {
            for (Produto produto : produtos) {
                adicionarProduto(produto);
            }
        }
    }

    public void adicionarProduto(Produto produto) {
        produto.setPedido(this);
        this.produtos.add(produto);
    }

    public Integer getTotalProdutos() {
        return produtos.stream()
                .mapToInt(p -> p.getQuantidade() != null ? p.getQuantidade() : 0)
                .sum();
    }

    public BigDecimal getValorTotal() {
        return produtos.stream()
                .map(Produto::getValorTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
