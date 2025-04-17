package com.cpmatmed.backend.service.impl;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.mapper.CompradorMapper;
import com.cpmatmed.backend.repository.CompradorRepository;
import com.cpmatmed.backend.service.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompradorServiceImpl implements CompradorService {

    private final CompradorRepository compradorRepository;

    @Autowired
    public CompradorServiceImpl(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    @Override
    public List<CompradorDTO> listarTodos() {
        return CompradorMapper.toDTOs(compradorRepository.findAll());
    }
}
