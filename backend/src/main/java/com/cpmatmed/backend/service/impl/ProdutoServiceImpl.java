package com.cpmatmed.backend.service.impl;

import com.cpmatmed.backend.dto.ProdutoDTO;
import com.cpmatmed.backend.mapper.ProdutoMapper;
import com.cpmatmed.backend.model.Produto;
import com.cpmatmed.backend.repository.ProdutoRepository;
import com.cpmatmed.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    // O ProdutoMapper não é mais necessário como campo, pois estamos utilizando o método estático diretamente.
    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoDTO> listarTodosProdutos() {
        return produtoRepository.findAll()
            .stream()
            .map(ProdutoMapper::toDTO) // Usando o método estático diretamente
            .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProdutoMapper.toDTO(produto); // Usando o método estático diretamente
    }

    @Override
    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.toEntity(produtoDTO); // Usando o método estático diretamente
        Produto produtoSalvo = produtoRepository.save(produto);
        return ProdutoMapper.toDTO(produtoSalvo); // Usando o método estático diretamente
    }

    @Override
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        produtoExistente.setNome(produtoDTO.getNome());
        produtoExistente.setDescricao(produtoDTO.getDescricao());
        produtoExistente.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        
        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return ProdutoMapper.toDTO(produtoAtualizado); // Usando o método estático diretamente
    }

    @Override
    public void excluirProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }
}
