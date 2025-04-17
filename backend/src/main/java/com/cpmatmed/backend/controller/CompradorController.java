package com.cpmatmed.backend.controller;

import com.cpmatmed.backend.dto.CompradorDTO;
import com.cpmatmed.backend.service.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
