package com.cpmatmed.backend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.dto.FornecedorRequest;
import com.cpmatmed.backend.exception.EntidadeNaoEncontradaException;
import com.cpmatmed.backend.mapper.FornecedorMapper;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorServiceImpl(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public List<FornecedorDTO> listarTodos() {
        return FornecedorMapper.toDTOList(fornecedorRepository.findAll());
    }

    @Override
    public FornecedorDTO salvar(FornecedorRequest fornecedorRequest) {
        return FornecedorMapper.toDTO(
            fornecedorRepository.save(
                FornecedorMapper.fromRequest(fornecedorRequest)
            )
        );
    }

    @Override
    public FornecedorDTO buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .map(FornecedorMapper::toDTO)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado com ID: " + id));
    }

    @Override
    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado com ID: " + id);
        }
        fornecedorRepository.deleteById(id);
    }
}