package br.com.homemade.repository;

import br.com.homemade.domain.Tipo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipo entity.
 */
@SuppressWarnings("unused")
public interface TipoRepository extends JpaRepository<Tipo,Long> {

}
