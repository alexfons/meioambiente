package br.com.homemade.repository;

import br.com.homemade.domain.Supre;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Supre entity.
 */
@SuppressWarnings("unused")
public interface SupreRepository extends JpaRepository<Supre,Long> {

}
