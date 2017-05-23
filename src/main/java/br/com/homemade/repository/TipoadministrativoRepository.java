package br.com.homemade.repository;

import br.com.homemade.domain.Tipoadministrativo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipoadministrativo entity.
 */
@SuppressWarnings("unused")
public interface TipoadministrativoRepository extends JpaRepository<Tipoadministrativo,Long> {

}
