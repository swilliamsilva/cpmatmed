
package com.cpmatmed.backend.dto;

import com.cpmatmed.backend.model.Pedido;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private String nomeComprador;
    private String nomeFornecedor;
    private Integer totalProdutos;
    private Double valorTotal;
	public void setPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}
	public void setId(Object id2) {
		// TODO Auto-generated method stub
		
	}
	public void setTotalProdutos(Integer totalProdutos2) {
		// TODO Auto-generated method stub
		
	}
	public void setValorTotal(Double valorTotal2) {
		// TODO Auto-generated method stub
		
	}
	public Object getNome() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getQuantidade() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getValorTotal() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setNomeComprador(Object nome) {
		// TODO Auto-generated method stub
		
	}
	public void setNomeFornecedor(Object nome) {
		// TODO Auto-generated method stub
		
	}
}
