package com.cpmatmed.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpmatmed.backend.dto.CompradorDTO;
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
    public CompradorDTO salvar(CompradorDTO compradorDTO) {
        return CompradorMapper.toDTO(
                compradorRepository.save(
                        CompradorMapper.fromIdAndNome(compradorDTO.getId(), compradorDTO.getNome())
                )
        );
    }

    @Override
    public CompradorDTO buscarPorId(Long id) {
        return compradorRepository.findById(id)
                .map(CompradorMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deletar(Long id) {
        compradorRepository.deleteById(id);
    }
}
