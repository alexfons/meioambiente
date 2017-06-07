package br.com.homemade.repository;

import br.com.homemade.domain.Contratoprojeto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Contratoprojeto entity.
 */
@SuppressWarnings("unused")
public interface ContratoprojetoRepository extends JpaRepository<Contratoprojeto,Long> {

}
