package br.com.homemade.repository;

import br.com.homemade.domain.Manifestacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Manifestacao entity.
 */
@SuppressWarnings("unused")
public interface ManifestacaoRepository extends JpaRepository<Manifestacao,Long> {

}
