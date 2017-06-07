package br.com.homemade.repository;

import br.com.homemade.domain.Fonte;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Fonte entity.
 */
@SuppressWarnings("unused")
public interface FonteRepository extends JpaRepository<Fonte,Long> {

}
