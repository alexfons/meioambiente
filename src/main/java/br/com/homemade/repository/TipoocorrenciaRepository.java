package br.com.homemade.repository;

import br.com.homemade.domain.Tipoocorrencia;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipoocorrencia entity.
 */
@SuppressWarnings("unused")
public interface TipoocorrenciaRepository extends JpaRepository<Tipoocorrencia,Long> {

}
