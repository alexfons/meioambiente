package br.com.homemade.repository;

import br.com.homemade.domain.Planoaquisicoes;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Planoaquisicoes entity.
 */
@SuppressWarnings("unused")
public interface PlanoaquisicoesRepository extends JpaRepository<Planoaquisicoes,Long> {

}
