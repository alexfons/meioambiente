package br.com.homemade.repository;

import br.com.homemade.domain.Ocorrenciainforme;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ocorrenciainforme entity.
 */
@SuppressWarnings("unused")
public interface OcorrenciainformeRepository extends JpaRepository<Ocorrenciainforme,Long> {

}
