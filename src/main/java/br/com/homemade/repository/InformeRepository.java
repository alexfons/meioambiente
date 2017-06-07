package br.com.homemade.repository;

import br.com.homemade.domain.Informe;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Informe entity.
 */
@SuppressWarnings("unused")
public interface InformeRepository extends JpaRepository<Informe,Long> {

    @Query("select distinct informe from Informe informe left join fetch informe.fotos left join fetch informe.ocorrenciasinformes")
    List<Informe> findAllWithEagerRelationships();

    @Query("select informe from Informe informe left join fetch informe.fotos left join fetch informe.ocorrenciasinformes where informe.id =:id")
    Informe findOneWithEagerRelationships(@Param("id") Long id);

}
