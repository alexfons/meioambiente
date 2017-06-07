package br.com.homemade.repository;

import br.com.homemade.domain.Valoresdesembolso;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Valoresdesembolso entity.
 */
@SuppressWarnings("unused")
public interface ValoresdesembolsoRepository extends JpaRepository<Valoresdesembolso,Long> {

}
