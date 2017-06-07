package br.com.homemade.repository;

import br.com.homemade.domain.Tipoautorizacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipoautorizacao entity.
 */
@SuppressWarnings("unused")
public interface TipoautorizacaoRepository extends JpaRepository<Tipoautorizacao,Long> {

}
