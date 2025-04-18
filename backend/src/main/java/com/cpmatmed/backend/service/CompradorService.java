package com.cpmatmed.backend.service;

import java.util.List;
import com.cpmatmed.backend.dto.CompradorDTO;

public interface CompradorService {

    List<CompradorDTO> listarTodos();

    CompradorDTO buscarPorId(Long id); // ← Adicione esta linha

    CompradorDTO salvar(CompradorDTO compradorDTO);

    void deletar(Long id);
}
