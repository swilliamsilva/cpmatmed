package com.cpmatmed.backend.dto;

public class CompradorRequest {

    private String nome;

    public CompradorRequest() {}

    public CompradorRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
