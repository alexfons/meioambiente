package br.com.homemade.repository;

import br.com.homemade.domain.Contabancaria;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Contabancaria entity.
 */
@SuppressWarnings("unused")
public interface ContabancariaRepository extends JpaRepository<Contabancaria,Long> {

}
