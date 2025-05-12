package com.cpmatmed.backend.dto;

import javax.validation.constraints.NotBlank;

public class FornecedorDTO {

    private Long id;

    @NotBlank(message = "O nome do fornecedor é obrigatório")
    private String nome;

    public FornecedorDTO() {}

    public FornecedorDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
