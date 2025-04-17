package com.cpmatmed.backend.mapper;

import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.model.Fornecedor;

import java.util.List;
import java.util.stream.Collectors;

public class FornecedorMapper {

    public static FornecedorDTO toDTO(Fornecedor fornecedor) {
        return new FornecedorDTO(fornecedor.getId(), fornecedor.getNome());
    }

    public static List<FornecedorDTO> toDTOList(List<Fornecedor> fornecedores) {
        return fornecedores.stream()
                .map(FornecedorMapper::toDTO)
                .collect(Collectors.toList());
    }
}
