package br.com.homemade.repository;

import br.com.homemade.domain.Edital;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Edital entity.
 */
@SuppressWarnings("unused")
public interface EditalRepository extends JpaRepository<Edital,Long> {

}
