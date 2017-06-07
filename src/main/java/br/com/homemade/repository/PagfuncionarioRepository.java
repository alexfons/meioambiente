package br.com.homemade.repository;

import br.com.homemade.domain.Pagfuncionario;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Pagfuncionario entity.
 */
@SuppressWarnings("unused")
public interface PagfuncionarioRepository extends JpaRepository<Pagfuncionario,Long> {

}
