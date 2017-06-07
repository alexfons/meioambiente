package br.com.homemade.repository;

import br.com.homemade.domain.Lancamentos;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Lancamentos entity.
 */
@SuppressWarnings("unused")
public interface LancamentosRepository extends JpaRepository<Lancamentos,Long> {

}
