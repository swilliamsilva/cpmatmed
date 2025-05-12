package com.cpmatmed.backend.dto;

import javax.validation.constraints.NotBlank;

public class FornecedorRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    public FornecedorRequest() {}

    public FornecedorRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}