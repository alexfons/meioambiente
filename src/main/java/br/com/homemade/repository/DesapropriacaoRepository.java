package br.com.homemade.repository;

import br.com.homemade.domain.Desapropriacao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Desapropriacao entity.
 */
@SuppressWarnings("unused")
public interface DesapropriacaoRepository extends JpaRepository<Desapropriacao,Long> {

}
