package br.com.homemade.repository;

import br.com.homemade.domain.Atividadeexecutadamensal;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Atividadeexecutadamensal entity.
 */
@SuppressWarnings("unused")
public interface AtividadeexecutadamensalRepository extends JpaRepository<Atividadeexecutadamensal,Long> {

}
