package br.com.homemade.repository;

import br.com.homemade.domain.Tipocertificadoconformidade;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipocertificadoconformidade entity.
 */
@SuppressWarnings("unused")
public interface TipocertificadoconformidadeRepository extends JpaRepository<Tipocertificadoconformidade,Long> {

}
