package br.com.homemade.repository;

import br.com.homemade.domain.Linha;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Linha entity.
 */
@SuppressWarnings("unused")
public interface LinhaRepository extends JpaRepository<Linha,Long> {

}
