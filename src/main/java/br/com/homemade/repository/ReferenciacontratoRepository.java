package br.com.homemade.repository;

import br.com.homemade.domain.Referenciacontrato;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Referenciacontrato entity.
 */
@SuppressWarnings("unused")
public interface ReferenciacontratoRepository extends JpaRepository<Referenciacontrato,Long> {

}
