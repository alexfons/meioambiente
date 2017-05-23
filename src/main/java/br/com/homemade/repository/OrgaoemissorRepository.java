package br.com.homemade.repository;

import br.com.homemade.domain.Orgaoemissor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Orgaoemissor entity.
 */
@SuppressWarnings("unused")
public interface OrgaoemissorRepository extends JpaRepository<Orgaoemissor,Long> {

}
