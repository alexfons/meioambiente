package br.com.homemade.repository;

import br.com.homemade.domain.Coluna;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Coluna entity.
 */
@SuppressWarnings("unused")
public interface ColunaRepository extends JpaRepository<Coluna,Long> {

}
