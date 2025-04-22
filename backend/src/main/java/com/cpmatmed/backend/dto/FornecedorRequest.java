package com.cpmatmed.backend.dto;

public class FornecedorRequest {

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
