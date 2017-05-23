package br.com.homemade.repository;

import br.com.homemade.domain.Documento;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Documento entity.
 */
@SuppressWarnings("unused")
public interface DocumentoRepository extends JpaRepository<Documento,Long> {

}
