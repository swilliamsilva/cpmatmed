package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.mapper.ProdutoMapper;
import com.cpmatmed.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public List<ProdutoDTO> listarTodos() {
        return produtoMapper.toProdutoDTOs(produtoRepository.findAll());
    }
}