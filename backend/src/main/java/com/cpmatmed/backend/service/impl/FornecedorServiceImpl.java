package com.cpmatmed.backend.service.impl;

import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.mapper.FornecedorMapper;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
