package br.com.homemade.repository;

import br.com.homemade.domain.Atividade;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Atividade entity.
 */
@SuppressWarnings("unused")
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {

}
