package com.cpmatmed.backend.service;

import java.util.List;
import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.dto.FornecedorRequest;

public interface FornecedorService {
    List<FornecedorDTO> listarTodos();
    FornecedorDTO salvar(FornecedorRequest fornecedorRequest); // Usa o Request
    FornecedorDTO buscarPorId(Long id);
    void deletar(Long id);
}