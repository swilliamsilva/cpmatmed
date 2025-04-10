package com.cpmatmed.backend.dto;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private String nomeComprador;
    private String nomeFornecedor;
    private Integer totalProdutos;
    private Double valorTotal;
}
