// CompradorRepository.java
package com.cpmatmed.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpmatmed.backend.model.Comprador;

public interface CompradorRepository extends JpaRepository<Comprador, Long> {}