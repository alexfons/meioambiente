package br.com.homemade.repository;

import br.com.homemade.domain.ResponsavelTipo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ResponsavelTipo entity.
 */
@SuppressWarnings("unused")
public interface ResponsavelTipoRepository extends JpaRepository<ResponsavelTipo,Long> {

}
