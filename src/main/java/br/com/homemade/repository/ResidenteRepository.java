package br.com.homemade.repository;

import br.com.homemade.domain.Residente;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Residente entity.
 */
@SuppressWarnings("unused")
public interface ResidenteRepository extends JpaRepository<Residente,Long> {

}
