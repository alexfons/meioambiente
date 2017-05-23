package br.com.homemade.repository;

import br.com.homemade.domain.Foto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Foto entity.
 */
@SuppressWarnings("unused")
public interface FotoRepository extends JpaRepository<Foto,Long> {

}
