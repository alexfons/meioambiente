package br.com.homemade.repository;

import br.com.homemade.domain.Ocorrenciapendencias;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ocorrenciapendencias entity.
 */
@SuppressWarnings("unused")
public interface OcorrenciapendenciasRepository extends JpaRepository<Ocorrenciapendencias,Long> {

}
