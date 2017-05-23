package br.com.homemade.repository;

import br.com.homemade.domain.Fiscal;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Fiscal entity.
 */
@SuppressWarnings("unused")
public interface FiscalRepository extends JpaRepository<Fiscal,Long> {

}
