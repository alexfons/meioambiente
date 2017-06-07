package br.com.homemade.repository;

import br.com.homemade.domain.Categoriainversao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Categoriainversao entity.
 */
@SuppressWarnings("unused")
public interface CategoriainversaoRepository extends JpaRepository<Categoriainversao,Long> {

}
