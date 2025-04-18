package com.cpmatmed.backend.service;

import java.util.List;
import com.cpmatmed.backend.dto.ProdutoDTO;

public interface ProdutoService {
    List<ProdutoDTO> listarTodos(); // corrigido: antes estava buscarTodos()
}
