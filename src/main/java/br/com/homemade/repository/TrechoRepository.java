package br.com.homemade.repository;

import br.com.homemade.domain.Trecho;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Trecho entity.
 */
@SuppressWarnings("unused")
public interface TrechoRepository extends JpaRepository<Trecho,Long> {

}
