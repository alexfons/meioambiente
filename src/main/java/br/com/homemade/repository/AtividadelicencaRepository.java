package br.com.homemade.repository;

import br.com.homemade.domain.Atividadelicenca;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Atividadelicenca entity.
 */
@SuppressWarnings("unused")
public interface AtividadelicencaRepository extends JpaRepository<Atividadelicenca,Long> {

}
