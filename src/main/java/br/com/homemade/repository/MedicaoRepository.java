package br.com.homemade.repository;

import br.com.homemade.domain.Medicao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Medicao entity.
 */
@SuppressWarnings("unused")
public interface MedicaoRepository extends JpaRepository<Medicao,Long> {

}
