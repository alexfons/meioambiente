package br.com.homemade.repository;

import br.com.homemade.domain.Editallote;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Editallote entity.
 */
@SuppressWarnings("unused")
public interface EditalloteRepository extends JpaRepository<Editallote,Long> {

}
