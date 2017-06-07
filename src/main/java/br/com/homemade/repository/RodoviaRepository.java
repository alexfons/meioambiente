package br.com.homemade.repository;

import br.com.homemade.domain.Rodovia;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Rodovia entity.
 */
@SuppressWarnings("unused")
public interface RodoviaRepository extends JpaRepository<Rodovia,Long> {

}
