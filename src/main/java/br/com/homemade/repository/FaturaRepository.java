package br.com.homemade.repository;

import br.com.homemade.domain.Fatura;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Fatura entity.
 */
@SuppressWarnings("unused")
public interface FaturaRepository extends JpaRepository<Fatura,Long> {

}
