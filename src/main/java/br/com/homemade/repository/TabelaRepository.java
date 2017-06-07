package br.com.homemade.repository;

import br.com.homemade.domain.Tabela;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tabela entity.
 */
@SuppressWarnings("unused")
public interface TabelaRepository extends JpaRepository<Tabela,Long> {

}
