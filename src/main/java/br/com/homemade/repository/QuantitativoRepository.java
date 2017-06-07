package br.com.homemade.repository;

import br.com.homemade.domain.Quantitativo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Quantitativo entity.
 */
@SuppressWarnings("unused")
public interface QuantitativoRepository extends JpaRepository<Quantitativo,Long> {

}
