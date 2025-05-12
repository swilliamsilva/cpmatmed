package com.cpmatmed.backend.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cpmatmed.backend.dto.FornecedorDTO;
import com.cpmatmed.backend.dto.FornecedorRequest;
import com.cpmatmed.backend.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> listarTodos() {
        List<FornecedorDTO> fornecedores = fornecedorService.listarTodos();
        return ResponseEntity.ok(fornecedores);
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> salvar(@Valid @RequestBody FornecedorRequest request) {
        FornecedorDTO salvo = fornecedorService.salvar(request);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}