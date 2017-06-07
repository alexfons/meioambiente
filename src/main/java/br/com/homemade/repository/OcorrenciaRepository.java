package br.com.homemade.repository;

import br.com.homemade.domain.Ocorrencia;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Ocorrencia entity.
 */
@SuppressWarnings("unused")
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia,Long> {

    @Query("select distinct ocorrencia from Ocorrencia ocorrencia left join fetch ocorrencia.fotos left join fetch ocorrencia.historicos left join fetch ocorrencia.registros")
    List<Ocorrencia> findAllWithEagerRelationships();

    @Query("select ocorrencia from Ocorrencia ocorrencia left join fetch ocorrencia.fotos left join fetch ocorrencia.historicos left join fetch ocorrencia.registros where ocorrencia.id =:id")
    Ocorrencia findOneWithEagerRelationships(@Param("id") Long id);

}
