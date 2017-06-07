package br.com.homemade.repository;

import br.com.homemade.domain.Informecertificadoirregularidade;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Informecertificadoirregularidade entity.
 */
@SuppressWarnings("unused")
public interface InformecertificadoirregularidadeRepository extends JpaRepository<Informecertificadoirregularidade,Long> {

}
