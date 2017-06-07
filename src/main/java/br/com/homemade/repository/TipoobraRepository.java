package br.com.homemade.repository;

import br.com.homemade.domain.Tipoobra;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipoobra entity.
 */
@SuppressWarnings("unused")
public interface TipoobraRepository extends JpaRepository<Tipoobra,Long> {

}
