package br.com.homemade.repository;

import br.com.homemade.domain.Faturacontrato;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Faturacontrato entity.
 */
@SuppressWarnings("unused")
public interface FaturacontratoRepository extends JpaRepository<Faturacontrato,Long> {

}
