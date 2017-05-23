package br.com.homemade.repository;

import br.com.homemade.domain.Historico;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Historico entity.
 */
@SuppressWarnings("unused")
public interface HistoricoRepository extends JpaRepository<Historico,Long> {

}
