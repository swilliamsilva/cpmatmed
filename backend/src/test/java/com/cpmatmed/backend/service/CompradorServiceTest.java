package com.cpmatmed.backend.service;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.mapper.CompradorMapper;
import com.cpmatmed.backend.model.Comprador;
import com.cpmatmed.backend.repository.CompradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompradorServiceTest {

    private CompradorRepository compradorRepository;
    private CompradorService compradorService;

    @BeforeEach
    void setUp() {
        compradorRepository = mock(CompradorRepository.class);
        compradorService = new CompradorService(compradorRepository);
    }

    @Test
    void deveListarTodosCompradores() {
        Comprador comprador1 = new Comprador(1L, "Maria");
        Comprador comprador2 = new Comprador(2L, "João");

        when(compradorRepository.findAll()).thenReturn(Arrays.asList(comprador1, comprador2));

        List<CompradorDTO> result = compradorService.listarTodos();

        assertEquals(2, result.size());
        assertEquals("Maria", result.get(0).getNome());
        assertEquals("João", result.get(1).getNome());

        verify(compradorRepository, times(1)).findAll();
    }
}
