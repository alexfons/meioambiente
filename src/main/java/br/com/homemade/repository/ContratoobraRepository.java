package br.com.homemade.repository;

import br.com.homemade.domain.Contratoobra;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Contratoobra entity.
 */
@SuppressWarnings("unused")
public interface ContratoobraRepository extends JpaRepository<Contratoobra,Long> {

}
