package com.cpmatmed.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Integer getTotalProdutos() {
        return produtos.stream()
                .mapToInt(produto -> produto.getQuantidade() != null ? produto.getQuantidade() : 0)
                .sum();
    }

    public Double getValorTotal() {
        return produtos.stream()
                .mapToDouble(produto -> produto.getValorTotal() != null ? produto.getValorTotal() : 0.0)
                .sum();
    }
}
