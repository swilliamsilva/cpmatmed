package com.cpmatmed.backend.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private Integer quantidade;
    private Double valorTotal;
}
