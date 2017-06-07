package br.com.homemade.repository;

import br.com.homemade.domain.Tipocontrato;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipocontrato entity.
 */
@SuppressWarnings("unused")
public interface TipocontratoRepository extends JpaRepository<Tipocontrato,Long> {

}
