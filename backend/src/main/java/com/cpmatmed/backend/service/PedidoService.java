package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.PedidoDTO;
import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.model.Pedido;
import com.cpmatmed.backend.model.Produto;

import repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll().stream().map(pedido -> {
            PedidoDTO dto = new PedidoDTO();
            dto.setId(pedido.getId());
            dto.setNomeComprador(pedido.getComprador().getNome());
            dto.setNomeFornecedor(pedido.getFornecedor().getNome());
            dto.setTotalProdutos(pedido.getTotalProdutos());
            dto.setValorTotal(pedido.getValorTotal());
            return dto;
        }).collect(Collectors.toList());
    }

   
    public List<ProdutoDTO> listarProdutosPorPedido(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        return pedido.getProdutos().stream()
                .map(produto -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setNome(produto.getNome());
                    dto.setQuantidade(produto.getQuantidade());
                    dto.setValorTotal(produto.getValorTotal());
                    return dto;
                }).collect(Collectors.toList());
  //  }

    
   // public Pedido criarPedido(Pedido pedido) {
   //     if (pedido.getTotalProdutos() == null || pedido.getTotalProdutos().isEmpty()) {
   //         throw new IllegalArgumentException("Pedido deve conter ao menos um produto");
   //     }

    }


	public Pedido criarPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}
}
