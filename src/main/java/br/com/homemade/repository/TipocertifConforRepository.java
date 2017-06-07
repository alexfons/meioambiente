package br.com.homemade.repository;

import br.com.homemade.domain.TipocertifConfor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TipocertifConfor entity.
 */
@SuppressWarnings("unused")
public interface TipocertifConforRepository extends JpaRepository<TipocertifConfor,Long> {

}
