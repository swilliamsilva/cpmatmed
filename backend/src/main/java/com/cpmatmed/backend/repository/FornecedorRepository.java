// FornecedorRepository.java
package com.cpmatmed.backend.repository;

import com.cpmatmed.backend.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {}