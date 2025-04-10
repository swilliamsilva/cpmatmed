package com.cpmatmed.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cpmatmed.backend.dto.PedidoDTO;

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
                .mapToInt(produto -> produto.getQuantidade() != null ? (int) produto.getQuantidade() : 0)
                .sum();
    }

    public Double getValorTotal() {
        return produtos.stream()
                .mapToDouble(produto -> produto.getValorTotal() != null ? produto.getValorTotal() : 0.0)
                .sum();
    }

	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public PedidoDTO getComprador() {
		// TODO Auto-generated method stub
		return null;
	}

	public PedidoDTO getFornecedor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<PedidoDTO> getProdutos() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setProdutos(List<Produto> of) {
		// TODO Auto-generated method stub
		
	}

	public void setFornecedor(Fornecedor fornecedor2) {
		// TODO Auto-generated method stub
		
	}

	public void setComprador(Comprador comprador2) {
		// TODO Auto-generated method stub
		
	}
}
