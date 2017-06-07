package br.com.homemade.repository;

import br.com.homemade.domain.Obraatividade;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Obraatividade entity.
 */
@SuppressWarnings("unused")
public interface ObraatividadeRepository extends JpaRepository<Obraatividade,Long> {

}
