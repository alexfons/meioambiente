package br.com.homemade.repository;

import br.com.homemade.domain.Referencia;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Referencia entity.
 */
@SuppressWarnings("unused")
public interface ReferenciaRepository extends JpaRepository<Referencia,Long> {

}
