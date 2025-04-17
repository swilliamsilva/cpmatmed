// ProdutoRepository.java
package com.cpmatmed.backend.repository;

import com.cpmatmed.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}