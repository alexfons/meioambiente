package br.com.homemade.repository;

import br.com.homemade.domain.Planocontas;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Planocontas entity.
 */
@SuppressWarnings("unused")
public interface PlanocontasRepository extends JpaRepository<Planocontas,Long> {

}
