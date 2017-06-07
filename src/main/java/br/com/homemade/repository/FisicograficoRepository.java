package br.com.homemade.repository;

import br.com.homemade.domain.Fisicografico;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Fisicografico entity.
 */
@SuppressWarnings("unused")
public interface FisicograficoRepository extends JpaRepository<Fisicografico,Long> {

}
