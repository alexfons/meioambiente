package br.com.homemade.repository;

import br.com.homemade.domain.Aditivocontrato;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Aditivocontrato entity.
 */
@SuppressWarnings("unused")
public interface AditivocontratoRepository extends JpaRepository<Aditivocontrato,Long> {

}
