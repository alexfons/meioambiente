package br.com.homemade.repository;

import br.com.homemade.domain.Contratoagente;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Contratoagente entity.
 */
@SuppressWarnings("unused")
public interface ContratoagenteRepository extends JpaRepository<Contratoagente,Long> {

}
