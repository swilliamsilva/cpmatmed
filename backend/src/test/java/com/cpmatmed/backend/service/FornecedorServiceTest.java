package com.cpmatmed.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.model.Fornecedor;
import com.cpmatmed.backend.repository.FornecedorRepository;
import com.cpmatmed.backend.service.impl.FornecedorServiceImpl;

class FornecedorServiceTest {

    private FornecedorRepository fornecedorRepository;
    private FornecedorService fornecedorService;

    @BeforeEach
    void setUp() {
        fornecedorRepository = mock(FornecedorRepository.class);
        fornecedorService = new FornecedorServiceImpl(fornecedorRepository);
    }

    @Test
    void deveListarTodosFornecedores() {
        Fornecedor fornecedor1 = new Fornecedor(1L, "Fornecedor A");
        Fornecedor fornecedor2 = new Fornecedor(2L, "Fornecedor B");

        when(fornecedorRepository.findAll()).thenReturn(Arrays.asList(fornecedor1, fornecedor2));

        List<FornecedorDTO> result = fornecedorService.listarTodos();

        assertEquals(2, result.size());
        assertEquals("Fornecedor A", result.get(0).getNome());
        assertEquals("Fornecedor B", result.get(1).getNome());

        verify(fornecedorRepository, times(1)).findAll();
    }
}
