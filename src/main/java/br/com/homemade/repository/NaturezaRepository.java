package br.com.homemade.repository;

import br.com.homemade.domain.Natureza;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Natureza entity.
 */
@SuppressWarnings("unused")
public interface NaturezaRepository extends JpaRepository<Natureza,Long> {

}
