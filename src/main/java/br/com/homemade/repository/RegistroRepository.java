package br.com.homemade.repository;

import br.com.homemade.domain.Registro;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Registro entity.
 */
@SuppressWarnings("unused")
public interface RegistroRepository extends JpaRepository<Registro,Long> {

}
