package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.mapper.FornecedorMapper;
import com.cpmatmed.backend.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<FornecedorDTO> listarTodos() {
        return FornecedorMapper.toDTOList(fornecedorRepository.findAll());
    }
}
