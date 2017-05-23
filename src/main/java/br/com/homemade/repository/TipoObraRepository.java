package br.com.homemade.repository;

import br.com.homemade.domain.TipoObra;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TipoObra entity.
 */
@SuppressWarnings("unused")
public interface TipoObraRepository extends JpaRepository<TipoObra,Long> {

}
