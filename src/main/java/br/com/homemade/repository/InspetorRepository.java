package br.com.homemade.repository;

import br.com.homemade.domain.Inspetor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Inspetor entity.
 */
@SuppressWarnings("unused")
public interface InspetorRepository extends JpaRepository<Inspetor,Long> {

}
