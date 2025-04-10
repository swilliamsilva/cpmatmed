package com.cpmatmed.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cpmatmed.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
