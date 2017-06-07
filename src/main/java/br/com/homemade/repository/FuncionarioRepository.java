package br.com.homemade.repository;

import br.com.homemade.domain.Funcionario;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Funcionario entity.
 */
@SuppressWarnings("unused")
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

}
