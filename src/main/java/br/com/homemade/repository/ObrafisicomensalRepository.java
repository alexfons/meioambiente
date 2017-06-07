package br.com.homemade.repository;

import br.com.homemade.domain.Obrafisicomensal;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Obrafisicomensal entity.
 */
@SuppressWarnings("unused")
public interface ObrafisicomensalRepository extends JpaRepository<Obrafisicomensal,Long> {

    @Query("select distinct obrafisicomensal from Obrafisicomensal obrafisicomensal left join fetch obrafisicomensal.atividadeexecutadamensals")
    List<Obrafisicomensal> findAllWithEagerRelationships();

    @Query("select obrafisicomensal from Obrafisicomensal obrafisicomensal left join fetch obrafisicomensal.atividadeexecutadamensals where obrafisicomensal.id =:id")
    Obrafisicomensal findOneWithEagerRelationships(@Param("id") Long id);

}
