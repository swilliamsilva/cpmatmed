package repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cpmatmed.backend.model.*;
public interface PedidoRepository extends JpaRepository<Pedido, Long> {}

