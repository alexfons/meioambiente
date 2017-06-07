package br.com.homemade.repository;

import br.com.homemade.domain.Responsavel;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Responsavel entity.
 */
@SuppressWarnings("unused")
public interface ResponsavelRepository extends JpaRepository<Responsavel,Long> {

}
