package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.mapper.CompradorMapper;
import com.cpmatmed.backend.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompradorService {
	@Autowired
	public CompradorService(CompradorRepository compradorRepository) {
	    this.compradorRepository = compradorRepository;
	}

    @Autowired
    private CompradorRepository compradorRepository;

    public List<CompradorDTO> listarTodos() {
        return CompradorMapper.toDTOs(compradorRepository.findAll());
    }
}
