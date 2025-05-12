package com.cpmatmed.backend.mapper;

import com.cpmatmed.backend.dto.CompradorDTO; // Importação adicionada
import com.cpmatmed.backend.dto.CompradorRequest; // Importação adicionada
import com.cpmatmed.backend.model.Comprador; // Certifique-se de que esta entidade existe
import java.util.List; // Importação adicionada
import java.util.stream.Collectors; // Importação adicionada

public class CompradorMapper {

    // Converte Request para Entidade (sem ID)
    public static Comprador fromRequest(CompradorRequest request) {
        Comprador comprador = new Comprador();
        comprador.setNome(request.getNome());
        return comprador;
    }

    // Converte Entidade para DTO
    public static CompradorDTO toDTO(Comprador comprador) {
        if (comprador == null) return null;
        return new CompradorDTO(comprador.getId(), comprador.getNome());
    }

    // Converte lista de entidades para lista de DTOs
    public static List<CompradorDTO> toDTOList(List<Comprador> compradores) {
        return compradores.stream()
                .map(CompradorMapper::toDTO)
                .collect(Collectors.toList()); // Corrigido
    }
}