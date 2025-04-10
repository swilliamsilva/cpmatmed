package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpmatmed.backend.model.*;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	void deleteAll();}

