package br.com.homemade.repository;

import br.com.homemade.domain.Obra;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Obra entity.
 */
@SuppressWarnings("unused")
public interface ObraRepository extends JpaRepository<Obra,Long> {

    @Query("select distinct obra from Obra obra left join fetch obra.contratosobras left join fetch obra.historicos")
    List<Obra> findAllWithEagerRelationships();

    @Query("select obra from Obra obra left join fetch obra.contratosobras left join fetch obra.historicos where obra.id =:id")
    Obra findOneWithEagerRelationships(@Param("id") Long id);

}
