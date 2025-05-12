package com.cpmatmed.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static class ErrorResponse {
        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final Map<String, String> details;

        public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, Map<String, String> details) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.details = details;
        }

        // Getters
        public LocalDateTime getTimestamp() { return timestamp; }
        public int getStatus() { return status; }
        public String getError() { return error; }
        public String getMessage() { return message; }
        public Map<String, String> getDetails() { return details; }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Dados inválidos",
            "Erro de validação nos campos",
            errors
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrity(DataIntegrityViolationException ex) {
        return new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            "Conflito de dados",
            ex.getRootCause() != null ? 
                ex.getRootCause().getMessage() : 
                "Violação de restrição do banco de dados",
            null
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntime(RuntimeException ex) {
        return new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Erro interno",
            ex.getMessage(),
            null
        );
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFound(EntidadeNaoEncontradaException ex) {
        return new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Recurso não encontrado",
            ex.getMessage(),
            null
        );
    }
}