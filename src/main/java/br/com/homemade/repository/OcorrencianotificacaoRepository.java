package br.com.homemade.repository;

import br.com.homemade.domain.Ocorrencianotificacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ocorrencianotificacao entity.
 */
@SuppressWarnings("unused")
public interface OcorrencianotificacaoRepository extends JpaRepository<Ocorrencianotificacao,Long> {

}
