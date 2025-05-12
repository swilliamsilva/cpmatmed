package com.cpmatmed.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpmatmed.backend.dto.FornecedorDTO;
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
    public FornecedorDTO salvar(FornecedorDTO fornecedorDTO) {
        return FornecedorMapper.toDTO(
            fornecedorRepository.save(
                FornecedorMapper.fromIdAndNome(fornecedorDTO.getId(), fornecedorDTO.getNome())
            )
        );
    }
}
