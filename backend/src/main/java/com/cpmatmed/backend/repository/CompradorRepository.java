// CompradorRepository.java
package com.cpmatmed.backend.repository;

import com.cpmatmed.backend.model.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<Comprador, Long> {}