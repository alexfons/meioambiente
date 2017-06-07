package br.com.homemade.repository;

import br.com.homemade.domain.Tipolicenca;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tipolicenca entity.
 */
@SuppressWarnings("unused")
public interface TipolicencaRepository extends JpaRepository<Tipolicenca,Long> {

}
