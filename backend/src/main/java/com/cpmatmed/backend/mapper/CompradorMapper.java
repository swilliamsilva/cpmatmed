package com.cpmatmed.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.model.Comprador;

public class CompradorMapper {

    public static Comprador fromIdAndNome(Long id, String nome) {
        Comprador comprador = new Comprador();
        comprador.setId(id);
        comprador.setNome(nome);
        return comprador;
    }

    public static CompradorDTO toDTO(Comprador comprador) {
        if (comprador == null) return null;
        return new CompradorDTO(comprador.getId(), comprador.getNome());
    }

    public static List<CompradorDTO> toDTOList(List<Comprador> compradores) {
        return compradores.stream()
                .map(CompradorMapper::toDTO)
                .collect(Collectors.toList());
    }
}
