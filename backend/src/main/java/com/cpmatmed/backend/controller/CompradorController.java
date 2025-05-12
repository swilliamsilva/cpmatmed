package com.cpmatmed.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.dto.CompradorRequest;
import com.cpmatmed.backend.service.CompradorService;

@RestController
@RequestMapping("/api/compradores")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class CompradorController {

    @Autowired
    private CompradorService compradorService;

    @GetMapping
    public ResponseEntity<List<CompradorDTO>> listarTodos() {
        List<CompradorDTO> compradores = compradorService.listarTodos();
        return ResponseEntity.ok(compradores);
    }

    @PostMapping
    public ResponseEntity<CompradorDTO> criar(@RequestBody CompradorRequest request) {
        CompradorDTO compradorDTO = new CompradorDTO();
        compradorDTO.setNome(request.getNome());

        CompradorDTO criado = compradorService.salvar(compradorDTO);
        return ResponseEntity.ok(criado);
    }
}
