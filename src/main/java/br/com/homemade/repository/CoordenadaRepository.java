package br.com.homemade.repository;

import br.com.homemade.domain.Coordenada;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Coordenada entity.
 */
@SuppressWarnings("unused")
public interface CoordenadaRepository extends JpaRepository<Coordenada,Long> {

}
