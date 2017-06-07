package br.com.homemade.repository;

import br.com.homemade.domain.Balanco;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Balanco entity.
 */
@SuppressWarnings("unused")
public interface BalancoRepository extends JpaRepository<Balanco,Long> {

}
