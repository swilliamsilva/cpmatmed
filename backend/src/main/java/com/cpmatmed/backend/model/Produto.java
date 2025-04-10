package com.cpmatmed.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer quantidade;

    private Double precoUnitario;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Produto(Object object, String string, int i, double d, Object object2) {
		// TODO Auto-generated constructor stub
	}

	public Double getValorTotal() {
        if (quantidade != null && precoUnitario != null) {
            return quantidade * precoUnitario;
        }
        return 0.0;
    }

	public Object getQuantidade() {
		// TODO Auto-generated method stub
		return null;
	}
}
