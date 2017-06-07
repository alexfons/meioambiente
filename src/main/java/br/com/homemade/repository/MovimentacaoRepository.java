package br.com.homemade.repository;

import br.com.homemade.domain.Movimentacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Movimentacao entity.
 */
@SuppressWarnings("unused")
public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Long> {

}
