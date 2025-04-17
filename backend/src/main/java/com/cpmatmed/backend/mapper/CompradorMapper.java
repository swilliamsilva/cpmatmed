package com.cpmatmed.backend.mapper;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.model.Comprador;

import java.util.List;
import java.util.stream.Collectors;

public class CompradorMapper {

    public static List<CompradorDTO> toDTOs(List<Comprador> compradores) {
        return compradores.stream()
                .map(c -> new CompradorDTO(c.getId(), c.getNome()))
                .collect(Collectors.toList());
    }
}
