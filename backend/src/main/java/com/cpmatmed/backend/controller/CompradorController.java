package com.cpmatmed.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpmatmed.backend.dto.CompradorDTO;
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
}
