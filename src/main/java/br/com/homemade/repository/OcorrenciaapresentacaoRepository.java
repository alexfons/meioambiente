package br.com.homemade.repository;

import br.com.homemade.domain.Ocorrenciaapresentacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ocorrenciaapresentacao entity.
 */
@SuppressWarnings("unused")
public interface OcorrenciaapresentacaoRepository extends JpaRepository<Ocorrenciaapresentacao,Long> {

}
