package com.cpmatmed.backend.service;

import java.util.List;
import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.dto.CompradorRequest;

public interface CompradorService {
    List<CompradorDTO> listarTodos();
    CompradorDTO buscarPorId(Long id);
    CompradorDTO salvar(CompradorRequest compradorRequest); 
    void deletar(Long id);
}