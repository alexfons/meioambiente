package br.com.homemade.repository;

import br.com.homemade.domain.Clausulascontratuais;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Clausulascontratuais entity.
 */
@SuppressWarnings("unused")
public interface ClausulascontratuaisRepository extends JpaRepository<Clausulascontratuais,Long> {

}
