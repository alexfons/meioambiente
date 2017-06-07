package br.com.homemade.repository;

import br.com.homemade.domain.Solicitacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Solicitacao entity.
 */
@SuppressWarnings("unused")
public interface SolicitacaoRepository extends JpaRepository<Solicitacao,Long> {

}
