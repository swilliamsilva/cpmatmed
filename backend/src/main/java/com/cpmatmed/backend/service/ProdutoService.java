package com.cpmatmed.backend.service;

import java.util.List;
import com.cpmatmed.backend.dto.ProdutoDTO;

public interface ProdutoService {
    List<ProdutoDTO> listarTodosProdutos();
    ProdutoDTO buscarProdutoPorId(Long id);
    ProdutoDTO salvarProduto(ProdutoDTO produtoDTO);
    ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO);
    void excluirProduto(Long id);
}
