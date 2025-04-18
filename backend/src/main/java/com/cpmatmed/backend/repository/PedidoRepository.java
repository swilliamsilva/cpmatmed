// PedidoRepository.java
package com.cpmatmed.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpmatmed.backend.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}