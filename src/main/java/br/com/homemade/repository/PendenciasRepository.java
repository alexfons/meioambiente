package br.com.homemade.repository;

import br.com.homemade.domain.Pendencias;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Pendencias entity.
 */
@SuppressWarnings("unused")
public interface PendenciasRepository extends JpaRepository<Pendencias,Long> {

    @Query("select distinct pendencias from Pendencias pendencias left join fetch pendencias.fotos left join fetch pendencias.ocorrenciaspendencias")
    List<Pendencias> findAllWithEagerRelationships();

    @Query("select pendencias from Pendencias pendencias left join fetch pendencias.fotos left join fetch pendencias.ocorrenciaspendencias where pendencias.id =:id")
    Pendencias findOneWithEagerRelationships(@Param("id") Long id);

}
