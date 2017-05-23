package br.com.homemade.repository;

import br.com.homemade.domain.Condicionante;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Condicionante entity.
 */
@SuppressWarnings("unused")
public interface CondicionanteRepository extends JpaRepository<Condicionante,Long> {

}
