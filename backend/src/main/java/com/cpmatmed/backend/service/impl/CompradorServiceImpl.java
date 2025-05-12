package com.cpmatmed.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.dto.CompradorRequest;
import com.cpmatmed.backend.exception.EntidadeNaoEncontradaException;
import com.cpmatmed.backend.mapper.CompradorMapper;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.service.CompradorService;

@Service
public class CompradorServiceImpl implements CompradorService {

    private final CompradorRepository compradorRepository;

    @Autowired
    public CompradorServiceImpl(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    @Override
    public List<CompradorDTO> listarTodos() {
        return compradorRepository.findAll().stream()
                .map(CompradorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompradorDTO salvar(CompradorRequest compradorRequest) {
        return CompradorMapper.toDTO(
            compradorRepository.save(
                CompradorMapper.fromRequest(compradorRequest)
            )
        );
    }

    @Override
    public CompradorDTO buscarPorId(Long id) {
        return compradorRepository.findById(id)
                .map(CompradorMapper::toDTO)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Comprador não encontrado com ID: " + id));
    }

    @Override
    public void deletar(Long id) {
        if (!compradorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Comprador não encontrado com ID: " + id);
        }
        compradorRepository.deleteById(id);
    }
}