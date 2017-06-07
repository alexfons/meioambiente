package br.com.homemade.repository;

import br.com.homemade.domain.Listamovimentacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Listamovimentacao entity.
 */
@SuppressWarnings("unused")
public interface ListamovimentacaoRepository extends JpaRepository<Listamovimentacao,Long> {

}
