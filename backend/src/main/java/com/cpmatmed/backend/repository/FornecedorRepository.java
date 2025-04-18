// FornecedorRepository.java
package com.cpmatmed.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpmatmed.backend.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {}